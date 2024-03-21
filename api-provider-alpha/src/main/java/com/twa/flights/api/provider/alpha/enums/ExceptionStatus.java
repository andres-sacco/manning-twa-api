package com.twa.flights.api.provider.alpha.enums;

public enum ExceptionStatus {

    EMPTY_ORIGINS(400_01, "From must have a value"), EMPTY_DESTINATIONS(400_02, "To must have a value"),
    EMPTY_DEPARTURES(400_03, "Departures must have a value"),
    INVALID_ADULTS_QUANTITY(400_04, "Adults value must be higher than zero"),
    INVALID_CHILDREN_QUANTITY(400_05, "Children must have a positive value"),
    INVALID_INFANT_QUANTITY(400_06, "Infants must have a positive value"),
    INVALID_DEPARTURES(400_07, "Departure must have a valid format"),
    ARRIVAL_BEFORE_DEPARTURE(400_08, "Arrival date es before departure date"),
    SEARCH_IN_THE_PAST(400_09, "you cannot make a search in the past"),
    INVALID_NUMBER_OF_PASSENGERS(400_10, "Invalid number of passengers. Maximum 9"),
    INVALID_ORIGIN_OR_DESTINATION(400_11, "At least one origin or destination have an invalid code"),
    ORIGIN_AND_DESTINATION_ARE_THE_SAME(400_12, "Origin and destination are the same"),
    DUPLICATED_ROUTES(400_13, "Some of the routes are duplicated");

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
