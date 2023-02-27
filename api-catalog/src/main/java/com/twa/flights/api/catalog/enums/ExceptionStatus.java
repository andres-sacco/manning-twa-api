package com.twa.flights.api.catalog.enums;

public enum ExceptionStatus {

    CITY_NOT_FOUND(404_01_01, "City not exist in the catalog"), COUNTRY_NOT_FOUND(404_01_02,
            "Country not exist in the catalog");

    private final int code;
    private final String message;

    ExceptionStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
