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
public class ResponseDescriptionTO {

    private ResultDescriptionTO response;

    public ResponseDescriptionTO() {
    }

    public ResultDescriptionTO getResponse() {
        return response;
    }

    public void setResponse(ResultDescriptionTO response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseDescriptionTO that = (ResponseDescriptionTO) o;

        if (response != null ? !response.equals(that.response) : that.response != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return response != null ? response.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ResponseDescriptionTO{" +
                "response=" + response +
                '}';
    }
}
