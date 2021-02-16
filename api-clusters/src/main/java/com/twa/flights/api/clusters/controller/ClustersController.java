package com.twa.flights.api.clusters.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twa.flights.api.clusters.controller.documentation.ClustersResources;
import com.twa.flights.api.clusters.service.ClustersService;
import com.twa.flights.api.clusters.validator.AvailabilityRequestValidator;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;
import com.twa.flights.common.dto.request.AvailabilityRequestDTO;

@RestController
@RequestMapping("/")
public class ClustersController implements ClustersResources {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClustersController.class);

    private final ClustersService clustersService;

    private final AvailabilityRequestValidator requestValidator;

    @Autowired
    public ClustersController(ClustersService clustersService, AvailabilityRequestValidator requestValidator) {
        this.clustersService = clustersService;
        this.requestValidator = requestValidator;
    }

    @Override
    public ResponseEntity<List<ItineraryDTO>> availability(AvailabilityRequestDTO request) {
        LOGGER.debug("Obtain all the itineraries with price");
        requestValidator.validate(request);

        List<ItineraryDTO> response = clustersService.availability(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
