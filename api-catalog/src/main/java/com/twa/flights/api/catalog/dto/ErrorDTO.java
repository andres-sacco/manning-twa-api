package com.twa.flights.api.catalog.dto;

import java.util.List;

public class ErrorDTO {

    private int code;
    private String shortDescription;
    private List<String> reasons;

    public ErrorDTO(int code, String shortDescription, List<String> reasons) {
        this.code = code;
        this.shortDescription = shortDescription;
        this.reasons = reasons;
    }

    public int getCode() {
        return code;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public List<String> getReasons() {
        return reasons;
    }

}
