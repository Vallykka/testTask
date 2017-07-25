package com.cft.testTask.connector;

import com.cft.testTask.Constants;
import com.cft.testTask.dto.ResponseDescriptionTO;
import com.cft.testTask.dto.ResultBankInfoTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author vallykka
 */
@Component
public class FacturaConnector {

    @Value("${main.url}")
    private String mainUrl;

    @Value("${description.url}")
    private String descriptionUrl;

    private Executor pool = Executors.newFixedThreadPool(5);
    private CompletionService<ResponseDescriptionTO> completionService = new ExecutorCompletionService<>(pool);

    public ResultBankInfoTO getBankInfo(Long bankId) {
        try {
            HttpResponse<InputStream> httpResponse = Unirest.post(mainUrl)
                    .header(Constants.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.getMimeType())
                    .field(Constants.BANK_ID, bankId)
                    .field(Constants.VERSION_OF_BD, Constants.DEFAULT_VERSION_OF_BD)
                    .field(Constants.SURE, Boolean.TRUE)
                    .asBinary();
            if (httpResponse.getStatus() != HttpStatus.OK.value()) return null;
            return convert(httpResponse);
        } catch (Exception e) {
            System.out.println("Couldn't get info for bank " + bankId + ".Reason: " + e.getMessage());
            return null;
        }
    }

    public Set<ResponseDescriptionTO> getDescriptions(Set<String> uuids) {
        List<Callable<ResponseDescriptionTO>> tasks = uuids.stream()
                .map(this::createRequestTask)
                .collect(Collectors.toList());

        Set<Future<ResponseDescriptionTO>> futures = new HashSet<Future<ResponseDescriptionTO>>();
        tasks.forEach(callable -> futures.add(completionService.submit(callable)));

        Set<ResponseDescriptionTO> response = new HashSet<>();
        Future<ResponseDescriptionTO> completed;
        ResponseDescriptionTO description;
        while (futures.size() > 0) {
            try {
                completed = completionService.take();
                futures.remove(completed);
                description = completed.get();
            } catch (InterruptedException|ExecutionException e) {
                //todo: add logging
                System.out.println("Couldn't get description. Reason: " + e.getMessage());
                continue;
            }

            response.add(description);
        }

        return response;
    }

    private Callable<ResponseDescriptionTO> createRequestTask(String uuid) {
        return () -> {
            try {
                HttpResponse<JsonNode> httpResponse = Unirest.post(descriptionUrl)
                        .header(Constants.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.getMimeType())
                        .field(Constants.OBJECT_UUID, uuid)
                        .asJson();
                if (httpResponse.getStatus() != HttpStatus.OK.value()) return null;

                String body = httpResponse.getBody().toString();

                ObjectMapper objectMapper = new ObjectMapper();
                ResponseDescriptionTO resultDescriptionTO = objectMapper.readValue(body, ResponseDescriptionTO.class);
                return resultDescriptionTO;

            } catch (Exception e) {
                //todo: add logging
                System.out.println("Couldn't get descriptions for bank with uuid " + uuid);
                return null;
            }
        };
    }

    private ResultBankInfoTO convert(HttpResponse<InputStream> httpResponse) throws IOException {
        InputStream body = httpResponse.getBody();
        ZipInputStream zipInputStream = new ZipInputStream(body);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            byte[] data = new byte[8192];
            int count = 0;
            BufferedOutputStream outputStream = new BufferedOutputStream(byteArrayOutputStream);
            while ((count = zipInputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, count);
            }
            outputStream.flush();
            outputStream.close();
            zipInputStream.closeEntry();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(byteArrayOutputStream.toByteArray(), ResultBankInfoTO.class);
    }
}
