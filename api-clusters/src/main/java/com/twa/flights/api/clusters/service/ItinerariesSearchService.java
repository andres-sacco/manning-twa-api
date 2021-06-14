package com.twa.flights.api.clusters.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twa.flights.api.clusters.connector.ItinerariesSearchConnector;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;
import com.twa.flights.common.dto.request.AvailabilityRequestDTO;

@Service
public class ItinerariesSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItinerariesSearchService.class);

    private ItinerariesSearchConnector itinerariesSearchConnector;

    @Autowired
    public ItinerariesSearchService(ItinerariesSearchConnector itinerariesSearchConnector) {
        this.itinerariesSearchConnector = itinerariesSearchConnector;
    }

    public List<ItineraryDTO> availability(AvailabilityRequestDTO request) {
        LOGGER.debug("Obtain the information about the flights");
        return itinerariesSearchConnector.availability(request);
    }

}
