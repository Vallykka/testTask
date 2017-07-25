package com.cft.testTask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Arrays;

/**
 * @author vallykka
 */
@JsonSerialize
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankPOIBankTO {

    public BankPOIBankTO() {
    }

    private BankPOITO[] objects;

    public BankPOITO[] getObjects() {
        return objects;
    }

    public void setObjects(BankPOITO[] objects) {
        this.objects = objects;
    }

    @Override
    public String toString() {
        return "BankPOIBankTO{" +
                "objects=" + Arrays.toString(objects) +
                '}';
    }
}
