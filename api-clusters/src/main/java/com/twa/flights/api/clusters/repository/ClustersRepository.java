package com.twa.flights.api.clusters.repository;

import java.util.List;

import com.twa.flights.api.clusters.dto.ClusterSearchDTO;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;
import com.twa.flights.common.dto.request.AvailabilityRequestDTO;

public interface ClustersRepository {

    ClusterSearchDTO insert(AvailabilityRequestDTO query, List<ItineraryDTO> itineraries);

    ClusterSearchDTO get(String id);
}
