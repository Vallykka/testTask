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
public class BankPOITO {

    private String uiid;

    public BankPOITO() {
    }

    public String getUiid() {
        return uiid;
    }

    public void setUiid(String uiid) {
        this.uiid = uiid;
    }

    @Override
    public String toString() {
        return "BankPOITO{" +
                "uiid='" + uiid + '\'' +
                '}';
    }
}
