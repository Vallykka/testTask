package com.cft.testTask.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * @author vallykka
 */
@JsonSerialize
@JsonDeserialize
public class BankResponseDto {
    private List<String> descriptions;

    public BankResponseDto() {
    }

    public BankResponseDto(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return "BankResponseDto{" +
                "descriptions=" + descriptions +
                '}';
    }
}
