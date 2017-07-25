package com.cft.testTask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author vallykka
 */
@JsonSerialize
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultBankInfoTO {

    public BankPOIUpdateTO object;

    public ResultBankInfoTO() {
    }

    public BankPOIUpdateTO getObject() {
        return object;
    }

    public void setObject(BankPOIUpdateTO object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "ResultTO{" +
                "object=" + object +
                '}';
    }
}
