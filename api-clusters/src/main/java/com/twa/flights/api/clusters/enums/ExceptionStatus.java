package com.twa.flights.api.clusters.enums;

public enum ExceptionStatus {

    EMPTY_ORIGINS(400_02_01, "From must have a value"), EMPTY_DESTINATIONS(400_02_02,
            "To must have a value"), EMPTY_DEPARTURES(400_02_03,
                    "Departures must have a value"), INVALID_ADULTS_QUANTITY(400_02_04,
                            "Adults value must be higher than zero"), INVALID_CHILDREN_QUANTITY(400_02_05,
                                    "Children must have a positive value"), INVALID_INFANT_QUANTITY(400_02_06,
                                            "Infants must have a positive value"), INVALID_DEPARTURES(400_02_07,
                                                    "Departure must have a valid format"), ARRIVAL_BEFORE_DEPARTURE(
                                                            400_02_08,
                                                            "Arrival date es before departure date"), SEARCH_IN_THE_PAST(
                                                                    400_02_09,
                                                                    "you cannot make a search in the past"), INVALID_NUMBER_OF_PASSENGERS(
                                                                            400_02_10,
                                                                            "Invalid number of passengers. Maximum 9"), INVALID_ORIGIN_OR_DESTINATION(
                                                                                    400_02_11,
                                                                                    "At least one origin or destination have an invalid code"), ORIGIN_AND_DESTINATION_ARE_THE_SAME(
                                                                                            400_02_12,
                                                                                            "Origin and destination are the same"), DUPLICATED_ROUTES(
                                                                                                    400_02_13,
                                                                                                    "Some of the routes are duplicated"), INCOMPLETE_CATALOG_INFORMATION(
                                                                                                            400_02_14,
                                                                                                            "The information about one of the city is not complete (code, country or continent is missing)"), SEARCH_NOT_FOUND_IN_REPOSITORY(
                                                                                                                    400_02_15,
                                                                                                                    "The information about the search is not available");

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
