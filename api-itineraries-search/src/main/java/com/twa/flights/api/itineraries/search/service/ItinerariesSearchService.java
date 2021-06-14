package com.twa.flights.api.itineraries.search.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import com.google.common.collect.Lists;
import com.twa.flights.api.itineraries.search.exception.TWAException;
import com.twa.flights.api.itineraries.search.facade.ProviderFacade;
import com.twa.flights.api.itineraries.search.helper.FlightDuplicationCheckHelper;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;
import com.twa.flights.common.dto.request.AvailabilityRequestDTO;

@Service
public class ItinerariesSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItinerariesSearchService.class);
    private final List<ProviderFacade> providers;
    private final ExecutorService executorService;
    private final FlightDuplicationCheckHelper flightDuplicationCheckHelper;

    @Autowired
    public ItinerariesSearchService(List<ProviderFacade> providers,
            FlightDuplicationCheckHelper flightDuplicationCheckHelper) {
        this.providers = providers;
        this.executorService = Executors.newFixedThreadPool(10);
        this.flightDuplicationCheckHelper = flightDuplicationCheckHelper;
    }

    public List<ItineraryDTO> availability(AvailabilityRequestDTO request) {
        LOGGER.debug("Obtain the information about the flights");

        List<ItineraryDTO> flights = Lists.newLinkedList();

        List<Future<List<ItineraryDTO>>> futureFlightsLists = makeProviderCalls(request);

        joinResults(flights, futureFlightsLists);

        flights = flightDuplicationCheckHelper.removeDuplicateFlights(flights);

        return flights;
    }

    // private function(s)

    private List<Future<List<ItineraryDTO>>> makeProviderCalls(final AvailabilityRequestDTO request) {

        return providers.stream().map(providerCall -> executorService.submit(() -> {
            List<ItineraryDTO> itineraries = Lists.newLinkedList();
            try {
                itineraries = providerCall.availability(request);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
            return itineraries;
        })).collect(Collectors.toList());

    }

    private void joinResults(List<ItineraryDTO> flights, List<Future<List<ItineraryDTO>>> futureFlightsLists) {

        for (Future<List<ItineraryDTO>> futureFlightsList : futureFlightsLists) {
            List<ItineraryDTO> rawItineraries = Lists.newLinkedList();

            try {
                rawItineraries = futureFlightsList.get();
                flights.addAll(rawItineraries);
            } catch (TWAException | ExecutionException | WebClientRequestException e) {
                LOGGER.error(e.getMessage(), e);
            } catch (InterruptedException e) {
                LOGGER.warn("Interrupted!", e);
                Thread.currentThread().interrupt();
            }
        }
    }

}
