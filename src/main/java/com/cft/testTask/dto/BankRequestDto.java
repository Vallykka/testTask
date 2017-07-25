package com.cft.testTask.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.UUID;

/**
 * @author vallykka
 */
@JsonSerialize
@JsonDeserialize
public class BankRequestDto {
    private UUID uuid;
    private Integer start;
    private Integer end;

    public BankRequestDto() {
    }

    public BankRequestDto(UUID uuid, Integer start, Integer end) {
        this.uuid = uuid;
        this.start = start;
        this.end = end;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "IntervalDto{" +
                "uuid=" + uuid +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
