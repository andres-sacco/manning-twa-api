package com.twa.flights.api.itineraries.search.facade;

import java.util.List;

import com.twa.flights.common.dto.enums.Provider;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;
import com.twa.flights.common.dto.request.AvailabilityRequestDTO;

public interface ProviderFacade {
    List<ItineraryDTO> availability(AvailabilityRequestDTO request);

    Provider getProvider();
}