package com.twa.flights.api.itineraries.search.helper;

import org.springframework.stereotype.Component;

import com.twa.flights.common.dto.itinerary.ItineraryDTO;
import com.twa.flights.common.dto.itinerary.LegDTO;
import com.twa.flights.common.dto.itinerary.SegmentDTO;

@Component
public class ItineraryKeyGeneratorHelper {

    private static final String SEGMENT_JOINER = "*";
    private static final String AND_CHAR = "&&";
    private static final String MIDDLESCORE_CHAR = "-";
    private static final String UNDERSCORE_CHAR = "_";
    private static final String AT_CHAR = "@";
    private static final String PIPELINE_CHAR = "|";
    private static final String OPEN_PARENTHESIS = "(";
    private static final String CLOSE_PARENTHESIS = ")";

    public String generateKeyForItinerary(ItineraryDTO itinerary) {
        StringBuilder sBuilder = new StringBuilder();

        sBuilder.append(itinerary.getProvider()).append(PIPELINE_CHAR);
        sBuilder.append(itinerary.getCarrier()).append(AT_CHAR);

        for (SegmentDTO segment : itinerary.getSegments()) {
            String sId = generateKeyForSegmentAndPriceInfo(segment);
            sBuilder.append(sId).append(SEGMENT_JOINER);
        }

        sBuilder.deleteCharAt(sBuilder.length() - 1);

        return sBuilder.toString();
    }

    private String generateKeyForSegmentAndPriceInfo(SegmentDTO segment) {

        StringBuilder id = new StringBuilder();
        id.append(PIPELINE_CHAR).append(this.generateKeyForSegment(segment)).append(AT_CHAR);

        return id.toString();
    }

    private String generateKeyForSegment(SegmentDTO segment) {
        StringBuilder id = new StringBuilder();
        String prefix = "";
        id.append(segment.getFlightDuration()).append(PIPELINE_CHAR);
        for (LegDTO leg : segment.getLegs()) {
            id.append(prefix);
            prefix = AND_CHAR;
            id.append(generateKeyForLeg(leg));
        }
        return id.toString();
    }

    private String generateKeyForLeg(LegDTO leg) {
        return new StringBuilder(leg.getOrigin()).append(MIDDLESCORE_CHAR).append(leg.getDestination())
                .append(OPEN_PARENTHESIS).append(leg.getNumber()).append(CLOSE_PARENTHESIS)
                .append(leg.getDepartureDate()).append(UNDERSCORE_CHAR).append(leg.getDepartureTime())
                .append(UNDERSCORE_CHAR).toString();
    }

}
