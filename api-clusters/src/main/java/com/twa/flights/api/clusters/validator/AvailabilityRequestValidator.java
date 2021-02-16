package com.twa.flights.api.clusters.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.twa.flights.api.clusters.dto.CityDTO;
import com.twa.flights.api.clusters.enums.ExceptionStatus;
import com.twa.flights.api.clusters.exception.BadRequestException;
import com.twa.flights.api.clusters.service.CatalogService;
import com.twa.flights.common.dto.request.AvailabilityRequestDTO;

@Component
public class AvailabilityRequestValidator {

    private static final String COMMA = ",";
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private final CatalogService catalogService;

    public AvailabilityRequestValidator(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public void validate(final AvailabilityRequestDTO request) {

        validateDeparture(request.getDeparture());

        validatePassengerCount(request);

        validateFromTo(request);
    }

    // private function(s)
    private void validateDeparture(final String departure) {

        if (departure == null) {
            throw new BadRequestException(ExceptionStatus.EMPTY_DEPARTURES);
        }

        final List<String> stringDates = Arrays.asList(departure.split(COMMA));

        final List<LocalDate> dates = Lists.newLinkedList();

        for (final String stringDate : stringDates) {
            try {
                dates.add(LocalDate.parse(stringDate, DateTimeFormatter.ofPattern(DATE_PATTERN)));
            } catch (final DateTimeParseException e) {
                throw new BadRequestException(ExceptionStatus.INVALID_DEPARTURES);
            }
        }

        dates.forEach(date -> {
            if (date.isBefore(LocalDate.now())) {
                throw new BadRequestException(ExceptionStatus.SEARCH_IN_THE_PAST);
            }
        });

        if (dates.size() > 1) {
            for (int i = 1; i < dates.size(); i++) {
                if (dates.get(i - 1).compareTo(dates.get(i)) > 0) {
                    throw new BadRequestException(ExceptionStatus.ARRIVAL_BEFORE_DEPARTURE);
                }
            }
        }
    }

    private void validatePassengerCount(final AvailabilityRequestDTO request) {
        if (request.getAdults() <= 0) {
            throw new BadRequestException(ExceptionStatus.INVALID_ADULTS_QUANTITY);
        }

        if (request.getChildren() < 0) {
            throw new BadRequestException(ExceptionStatus.INVALID_CHILDREN_QUANTITY);
        }

        if (request.getInfants() < 0) {
            throw new BadRequestException(ExceptionStatus.INVALID_INFANT_QUANTITY);
        }

        if (request.getAdults() + request.getChildren() > 9) {
            throw new BadRequestException(ExceptionStatus.INVALID_NUMBER_OF_PASSENGERS);
        }
    }

    private void validateFromTo(AvailabilityRequestDTO request) {

        validateFromToEmptyRequestFields(request);

        final List<String> list = Lists.newArrayList();
        final List<String> origins = Lists.newArrayList(request.getFrom().split(","));
        final List<String> destinations = Lists.newArrayList(request.getTo().split(","));

        origins.removeIf(ObjectUtils::isEmpty);
        destinations.removeIf(ObjectUtils::isEmpty);

        list.addAll(origins);
        list.addAll(destinations);

        validateFromToValidCodes(list);
        validateFromToRoutes(origins, destinations);
    }

    private void validateFromToEmptyRequestFields(AvailabilityRequestDTO request) {
        if (!StringUtils.hasLength(request.getFrom())) {
            throw new BadRequestException(ExceptionStatus.EMPTY_ORIGINS);
        }

        if (!StringUtils.hasLength(request.getTo())) {
            throw new BadRequestException(ExceptionStatus.EMPTY_DESTINATIONS);
        }
    }

    private void validateFromToRoutes(final List<String> origins, final List<String> destinations) {
        List<String> routes = Lists.newArrayList();
        for (int i = 0; i < origins.size(); i++) {
            if (origins.get(i).equals(destinations.get(i))) {
                throw new BadRequestException(ExceptionStatus.ORIGIN_AND_DESTINATION_ARE_THE_SAME);
            }
            final String route = origins.get(i).concat(destinations.get(i));
            if (routes.contains(route)) {
                throw new BadRequestException(ExceptionStatus.DUPLICATED_ROUTES);
            }
            routes.add(route);
        }
    }

    private void validateFromToValidCodes(final List<String> list) {
        for (String code : list) {
            if (code.length() > 3) {
                throw new BadRequestException(ExceptionStatus.INVALID_ORIGIN_OR_DESTINATION);
            } else {
                validateCodeWithCatalog(code);
            }
        }
    }

    private void validateCodeWithCatalog(String code) {
        try {
            CityDTO city = catalogService.getCity(code);

            if (!StringUtils.hasLength(city.getCode()) || city.getCountry() == null
                    || !StringUtils.hasLength(city.getCountry().getCode()) || city.getCountry().getContinent() == null
                    || !StringUtils.hasLength(city.getCountry().getContinent().getCode())) {
                throw new BadRequestException(ExceptionStatus.INCOMPLETE_CATALOG_INFORMATION);
            }
        } catch (Exception e) {
            throw new BadRequestException(ExceptionStatus.INCOMPLETE_CATALOG_INFORMATION);
        }
    }
}
