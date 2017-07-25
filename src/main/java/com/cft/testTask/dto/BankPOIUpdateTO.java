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
public class BankPOIUpdateTO {

    private BankPOIBankTO[] generalUpdates;
    private BankPOIBankTO[] mainUpdates;

    public BankPOIUpdateTO() {
    }

    public BankPOIBankTO[] getGeneralUpdates() {
        return generalUpdates;
    }

    public void setGeneralUpdates(BankPOIBankTO[] generalUpdates) {
        this.generalUpdates = generalUpdates;
    }

    public BankPOIBankTO[] getMainUpdates() {
        return mainUpdates;
    }

    public void setMainUpdates(BankPOIBankTO[] mainUpdates) {
        this.mainUpdates = mainUpdates;
    }

    @Override
    public String toString() {
        return "BankPOIUpdateTO{" +
                "generalUpdates=" + Arrays.toString(generalUpdates) +
                ", mainUpdates=" + Arrays.toString(mainUpdates) +
                '}';
    }
}
