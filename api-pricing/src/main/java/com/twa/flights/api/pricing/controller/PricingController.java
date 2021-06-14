package com.twa.flights.api.pricing.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twa.flights.api.pricing.controller.documentation.PricingResources;
import com.twa.flights.api.pricing.dto.UpdatedPriceInfoDTO;
import com.twa.flights.api.pricing.service.PricingService;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;

@RestController
@RequestMapping("/")
public class PricingController implements PricingResources {

    private static final Logger LOGGER = LoggerFactory.getLogger(PricingController.class);

    private PricingService pricingService;

    @Autowired
    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @Override
    public ResponseEntity<List<UpdatedPriceInfoDTO>> priceItineraries(List<ItineraryDTO> itineraries) {
        LOGGER.debug("Pricing all the itineraries");
        List<UpdatedPriceInfoDTO> response = pricingService.priceItineraries(itineraries);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
