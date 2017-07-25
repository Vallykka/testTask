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
public class ResultDescriptionTO {

    private BankPOIDescriptionTO object;

    public ResultDescriptionTO() {
    }

    public BankPOIDescriptionTO getObject() {
        return object;
    }

    public void setObject(BankPOIDescriptionTO object) {
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultDescriptionTO that = (ResultDescriptionTO) o;

        if (object != null ? !object.equals(that.object) : that.object != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return object != null ? object.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ResultDescriptionTO{" +
                "object=" + object +
                '}';
    }
}
