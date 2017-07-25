package com.cft.testTask.service.impl;

import com.cft.testTask.connector.FacturaConnector;
import com.cft.testTask.dto.BankPOIBankTO;
import com.cft.testTask.dto.BankPOIDescriptionTO;
import com.cft.testTask.dto.BankPOITO;
import com.cft.testTask.dto.BankPOIUpdateTO;
import com.cft.testTask.dto.BankResponseDto;
import com.cft.testTask.dto.ResponseDescriptionTO;
import com.cft.testTask.dto.ResultBankInfoTO;
import com.cft.testTask.dto.ResultDescriptionTO;
import com.cft.testTask.service.BankService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author vallykka
 */
@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private FacturaConnector facturaConnector;

    @Override
    public BankResponseDto getDescriptions(Long bankId) {
        if (bankId == null) return new BankResponseDto();

        ResultBankInfoTO bankInfo = facturaConnector.getBankInfo(bankId);
        Set<String> uuids = prepareUuids(bankInfo);
        if (uuids.isEmpty()) return new BankResponseDto();

        Set<ResponseDescriptionTO> descriptions = facturaConnector.getDescriptions(uuids);
        List<String> descriptionList = descriptions.stream()
                .map(ResponseDescriptionTO::getResponse)
                .filter(Objects::nonNull)
                .map(ResultDescriptionTO::getObject)
                .filter(Objects::nonNull)
                .map(BankPOIDescriptionTO::getDescription)
                .filter(StringUtils::isNotBlank)
                .sorted((str1, str2) -> str1.compareTo(str2))
                .collect(Collectors.toList());

        return new BankResponseDto(descriptionList);
    }

    private Set<String> prepareUuids(ResultBankInfoTO bankInfo) {
        Set<String> uuids = new HashSet<>();
        Optional<BankPOIBankTO[]> generalUpdates = Optional.ofNullable(bankInfo)
            .map(ResultBankInfoTO::getObject)
            .map(BankPOIUpdateTO::getGeneralUpdates);
        Optional<BankPOIBankTO[]> mainUpdates = Optional.ofNullable(bankInfo)
                .map(ResultBankInfoTO::getObject)
                .map(BankPOIUpdateTO::getMainUpdates);
        if (generalUpdates.isPresent()) {
            uuids.addAll(getUuids(generalUpdates));
        }
        if (mainUpdates.isPresent()) {
            uuids.addAll(getUuids(mainUpdates));
        }

        return uuids;
    }

    private Set<String> getUuids(Optional<BankPOIBankTO[]> bankUpdates) {
        return Arrays.stream(bankUpdates.get())
                .map(BankPOIBankTO::getObjects)
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .map(BankPOITO::getUiid)
                .collect(Collectors.toSet());
    }
}
