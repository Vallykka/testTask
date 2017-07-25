package com.cft.testTask.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author vallykka
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankControllerTest {

    private final static String URL = "/api/bank/description";
    private final static String PARAMETER_NAME = "bankId";
    private final static Long PARAMETER = 68174L;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetDescription() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                .param(PARAMETER_NAME, PARAMETER.toString())
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
