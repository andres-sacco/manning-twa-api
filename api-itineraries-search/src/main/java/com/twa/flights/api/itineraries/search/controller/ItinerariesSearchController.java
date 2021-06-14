package com.twa.flights.api.itineraries.search.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twa.flights.api.itineraries.search.controller.documentation.ItinerariesSearchResources;
import com.twa.flights.api.itineraries.search.service.ItinerariesSearchService;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;
import com.twa.flights.common.dto.request.AvailabilityRequestDTO;

@RestController
@RequestMapping("/")
public class ItinerariesSearchController implements ItinerariesSearchResources {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItinerariesSearchController.class);

    private final ItinerariesSearchService itinerariesSearchService;

    @Autowired
    public ItinerariesSearchController(ItinerariesSearchService itinerariesSearchService) {
        this.itinerariesSearchService = itinerariesSearchService;
    }

    @Override
    public ResponseEntity<List<ItineraryDTO>> availability(AvailabilityRequestDTO request) {
        LOGGER.debug("Obtain all the itineraries without price");

        List<ItineraryDTO> response = itinerariesSearchService.availability(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
