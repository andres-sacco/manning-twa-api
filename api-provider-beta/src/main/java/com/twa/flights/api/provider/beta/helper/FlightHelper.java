package com.twa.flights.api.provider.beta.helper;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.google.common.collect.Iterables;
import com.twa.flights.api.provider.beta.dto.CityDTO;
import com.twa.flights.api.provider.beta.service.CatalogService;
import com.twa.flights.common.dto.itinerary.LegDTO;
import com.twa.flights.common.dto.itinerary.SegmentDTO;

@Component
public class FlightHelper {

    private final CatalogService catalogService;

    private static final DateTimeFormatter LOCATION_DATETIME_FORMATTER = DateTimeFormat
            .forPattern("yyyy-MM-dd'T'HH:mm");

    @Autowired
    public FlightHelper(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public String timeBetweenDateTime(String fromDate, String fromTime, String fromTimezone, String toDate,
            String toTime, String toTimezone) {

        DateTime from = LOCATION_DATETIME_FORMATTER.parseDateTime(fromDate + "T" + fromTime);
        DateTime to = LOCATION_DATETIME_FORMATTER.parseDateTime(toDate + "T" + toTime);

        if (!ObjectUtils.isEmpty(fromTimezone) && !ObjectUtils.isEmpty(toTimezone)) {
            from = from.withZoneRetainFields(DateTimeZone.forID(fromTimezone));
            to = to.withZoneRetainFields(DateTimeZone.forID(toTimezone));
        }

        return timeBetweenDateTime(from, to);
    }

    public String timeBetweenDateTime(DateTime from, DateTime to) {
        Duration duration = new Duration(from.getMillis(), to.getMillis());
        long val = duration.getStandardMinutes();
        long hours = val / 60;
        long minutes = val % 60;

        return (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes);
    }

    public String calculateFlightDuration(SegmentDTO segment) {
        LegDTO firstLeg = Iterables.getFirst(segment.getLegs(), null);
        LegDTO lastLeg = Iterables.getLast(segment.getLegs(), null);

        CityDTO originCity = catalogService.getCity(firstLeg.getOrigin());
        CityDTO destinationCity = catalogService.getCity(lastLeg.getDestination());

        return timeBetweenDateTime(firstLeg.getDepartureDate(), firstLeg.getDepartureTime(), originCity.getTimeZone(),
                lastLeg.getArrivalDate(), lastLeg.getArrivalTime(), destinationCity.getTimeZone());
    }

    public String calculateFlightDuration(LegDTO leg) {
        CityDTO originCity = catalogService.getCity(leg.getOrigin());
        CityDTO destinationCity = catalogService.getCity(leg.getDestination());

        return timeBetweenDateTime(leg.getDepartureDate(), leg.getDepartureTime(), originCity.getTimeZone(),
                leg.getArrivalDate(), leg.getArrivalTime(), destinationCity.getTimeZone());
    }
}
