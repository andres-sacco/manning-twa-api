package com.twa.flights.api.clusters.exception;

import com.twa.flights.api.clusters.enums.ExceptionStatus;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -8999197576638181441L;

    private final ExceptionStatus status;

    public BadRequestException(ExceptionStatus status) {
        this.status = status;
    }

    public int getCode() {
        return status.getCode();
    }

    @Override
    public String getMessage() {
        return status.getMessage();
    }

}
