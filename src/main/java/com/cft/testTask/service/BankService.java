package com.cft.testTask.service;

import com.cft.testTask.dto.BankResponseDto;

/**
 * @author vallykka
 */
public interface BankService {

    /**
     * gets descriptions for bank by its identifier
     */
    BankResponseDto getDescriptions(Long bankId);
}
