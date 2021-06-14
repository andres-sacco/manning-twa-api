package com.twa.flights.api.provider.alpha.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twa.flights.api.provider.alpha.controller.documentation.ProviderResources;
import com.twa.flights.api.provider.alpha.service.ProviderService;
import com.twa.flights.api.provider.alpha.validator.AvailabilityRequestValidator;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;
import com.twa.flights.common.dto.request.AvailabilityRequestDTO;

@RestController
@RequestMapping("/")
public class ProviderController implements ProviderResources {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderController.class);

    private ProviderService providerService;

    private AvailabilityRequestValidator requestValidator;

    @Autowired
    public ProviderController(AvailabilityRequestValidator requestValidator, ProviderService providerService) {
        this.requestValidator = requestValidator;
        this.providerService = providerService;
    }

    @Override
    public ResponseEntity<List<ItineraryDTO>> availability(AvailabilityRequestDTO request) {
        LOGGER.debug("Obtain all the itineraries form provider beta");

        requestValidator.validate(request);

        List<ItineraryDTO> response = providerService.availability(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
