package com.twa.flights.api.clusters.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twa.flights.common.dto.itinerary.ItineraryDTO;
import com.twa.flights.common.dto.request.AvailabilityRequestDTO;

@Service
public class ClustersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClustersService.class);

    private final ItinerariesSearchService itinerariesSearchService;
    private final PricingService pricingService;

    @Autowired
    public ClustersService(ItinerariesSearchService itinerariesSearchService, PricingService pricingService) {
        this.itinerariesSearchService = itinerariesSearchService;
        this.pricingService = pricingService;
    }

    public List<ItineraryDTO> availability(AvailabilityRequestDTO request) {
        LOGGER.debug("begin the search");

        List<ItineraryDTO> itineraries = itinerariesSearchService.availability(request);

        itineraries = pricingService.priceItineraries(itineraries);

        return itineraries.stream()
                .sorted((itineraryOne, itineraryTwo) -> itineraryOne.getPriceInfo().getTotalAmount()
                        .compareTo(itineraryTwo.getPriceInfo().getTotalAmount()))
                .limit(request.getAmount()).collect(Collectors.toList());
    }

}
