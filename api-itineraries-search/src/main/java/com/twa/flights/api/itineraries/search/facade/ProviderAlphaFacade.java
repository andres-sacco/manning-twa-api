package com.twa.flights.api.itineraries.search.facade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twa.flights.api.itineraries.search.connector.ProviderAlphaConnector;
import com.twa.flights.common.dto.enums.Provider;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;
import com.twa.flights.common.dto.request.AvailabilityRequestDTO;

@Component
public class ProviderAlphaFacade implements ProviderFacade {

    static final Logger LOGGER = LoggerFactory.getLogger(ProviderAlphaFacade.class);

    ProviderAlphaConnector itinerariesSearchConnector;

    @Autowired
    public ProviderAlphaFacade(ProviderAlphaConnector itinerariesSearchConnector) {
        this.itinerariesSearchConnector = itinerariesSearchConnector;
    }

    public List<ItineraryDTO> availability(AvailabilityRequestDTO request) {
        LOGGER.debug("Obtain the information about the flights");
        return itinerariesSearchConnector.availability(request);
    }

    @Override
    public Provider getProvider() {
        return Provider.ALPHA;
    }

}
