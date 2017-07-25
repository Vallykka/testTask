package com.cft.testTask.controller;

import com.cft.testTask.dto.BankResponseDto;
import com.cft.testTask.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author vallykka
 */
@Controller
@RequestMapping(value = "api/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @RequestMapping(value = "description", method = RequestMethod.GET)
    public @ResponseBody BankResponseDto getDescriptions(@RequestParam Long bankId) {
        //todo: add logging
        return bankService.getDescriptions(bankId);
    }
}
