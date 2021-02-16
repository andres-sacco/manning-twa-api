package com.twa.flights.api.clusters.exception;

public class TWAException extends RuntimeException {

    private static final long serialVersionUID = -5274357034970222787L;

    public TWAException(String shortDescription) {
        super(shortDescription);
    }
}
