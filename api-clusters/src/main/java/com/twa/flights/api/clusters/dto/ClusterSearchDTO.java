package com.twa.flights.api.clusters.dto;

import java.util.List;

import com.twa.flights.common.dto.itinerary.ItineraryDTO;

public class ClusterSearchDTO {

    private String id;
    private List<ItineraryDTO> itineraries;

    public ClusterSearchDTO() {

    }

    public ClusterSearchDTO(String id, List<ItineraryDTO> itineraries) {
        this.id = id;
        this.itineraries = itineraries;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ItineraryDTO> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<ItineraryDTO> itineraries) {
        this.itineraries = itineraries;
    }

}
