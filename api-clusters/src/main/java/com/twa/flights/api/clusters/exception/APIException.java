package com.twa.flights.api.clusters.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

public class APIException extends RuntimeException {

    private static final long serialVersionUID = -5274357034970222787L;

    private HttpStatus status;
    private int code;
    private String shortDescription;
    private List<String> reasons;

    public APIException(HttpStatus status, int code) {
        super("our best engineers are working to do a better place for you!");
        this.status = status;
        this.code = code;
    }

    public APIException(HttpStatus status, int code, List<String> reasons, String shortDescription) {
        super("our best engineers are working to do a better place for you!");
        this.status = status;
        this.code = code;
        this.reasons = reasons;
        this.shortDescription = shortDescription;
    }

    public APIException(HttpStatus status, int code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public APIException(HttpStatus status, int code, String message, List<String> reasons, String shortDescription) {
        super(message);
        this.status = status;
        this.code = code;
        this.reasons = reasons;
        this.shortDescription = shortDescription;
    }

    public APIException(HttpStatus status, int code, Throwable cause) {
        super(cause);
        this.status = status;
        this.code = code;
    }

    public APIException(HttpStatus status, int code, Throwable cause, List<String> reasons, String shortDescription) {
        super(cause);
        this.status = status;
        this.code = code;
        this.reasons = reasons;
        this.shortDescription = shortDescription;
    }

    public APIException(HttpStatus status, int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public APIException(HttpStatus status, int code, String message, Throwable cause, List<String> reasons,
            String shortDescription) {
        super(message, cause);
        this.status = status;
        this.code = code;
        this.reasons = reasons;
        this.shortDescription = shortDescription;
    }

    public int getCode() {
        return code;
    }

    public List<String> getReasons() {
        return reasons;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
