package com.twa.flights.api.provider.alpha.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.twa.flights.api.provider.alpha.connector.AlphaConnector;
import com.twa.flights.api.provider.alpha.helper.FlightHelper;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;
import com.twa.flights.common.dto.itinerary.LegDTO;
import com.twa.flights.common.dto.itinerary.SegmentDTO;
import com.twa.flights.common.dto.request.AvailabilityRequestDTO;

@Service
public class ProviderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderService.class);

    private final AlphaConnector alphaConnector;
    private final FlightHelper flightHelper;

    @Autowired
    public ProviderService(AlphaConnector alphaConnector, FlightHelper flightHelper) {
        this.alphaConnector = alphaConnector;
        this.flightHelper = flightHelper;
    }

    public List<ItineraryDTO> availability(AvailabilityRequestDTO availabilityRequest) {
        List<ItineraryDTO> itineraries = alphaConnector.availability(availabilityRequest);

        return calculateFlightDuration(itineraries);
    }

    private List<ItineraryDTO> calculateFlightDuration(List<ItineraryDTO> itineraries) {
        List<ItineraryDTO> filteredItineraries = Lists.newArrayList();

        for (ItineraryDTO itinerary : itineraries) {
            if (calculateFlightDuration(itinerary)) {
                filteredItineraries.add(itinerary);
            }
        }

        return filteredItineraries;
    }

    private boolean calculateFlightDuration(ItineraryDTO itinerary) {
        boolean completedItinerary = Boolean.FALSE;
        try {
            for (SegmentDTO segment : itinerary.getSegments()) {
                calculateFlightDuration(segment);
            }
            completedItinerary = Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error("Occur an error calculating the flight duration of id {}", itinerary.getId());
        }
        return completedItinerary;
    }

    private void calculateFlightDuration(SegmentDTO seg) {

        for (LegDTO leg : seg.getLegs()) {
            leg.setFlightDuration(flightHelper.calculateFlightDuration(leg));
        }

        seg.setFlightDuration(flightHelper.calculateFlightDuration(seg));
    }
}
