package com.twa.flights.api.clusters.helper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.twa.flights.common.dto.request.AvailabilityRequestDTO;

@Component
public class FlightIdGeneratorHelper {

    private static final String EQUALS_CHAR = "=";
    private static final String UNDERSCORE_CHAR = "_";

    public String generate(AvailabilityRequestDTO availabilityRequest) {

        StringBuilder id = new StringBuilder();

        String prefix = "TWA";

        id.append(prefix).append(UNDERSCORE_CHAR);

        id.append("g").append(EQUALS_CHAR).append(LocalDateTime.now().toString()).append(UNDERSCORE_CHAR);

        id.append("f").append(EQUALS_CHAR).append(availabilityRequest.getFrom()).append(UNDERSCORE_CHAR);

        id.append("t").append(EQUALS_CHAR).append(availabilityRequest.getTo()).append(UNDERSCORE_CHAR);

        id.append("d").append(EQUALS_CHAR).append(availabilityRequest.getDeparture()).append(UNDERSCORE_CHAR);

        id.append("a").append(EQUALS_CHAR).append(availabilityRequest.getAdults()).append(UNDERSCORE_CHAR);

        id.append("c").append(EQUALS_CHAR)
                .append(availabilityRequest.getChildren() != null ? availabilityRequest.getChildren() : 0)
                .append(UNDERSCORE_CHAR);

        id.append("i").append(EQUALS_CHAR)
                .append(availabilityRequest.getInfants() != null ? availabilityRequest.getInfants() : 0);

        return id.toString();
    }

}
