package com.twa.flights.api.clusters.dto;

import java.util.List;

import com.twa.flights.api.clusters.dto.response.PaginationDTO;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;

public class ClusterSearchDTO {

    private String id;
    private PaginationDTO pagination;
    private List<ItineraryDTO> itineraries;

    public ClusterSearchDTO() {

    }

    public ClusterSearchDTO(String id, List<ItineraryDTO> itineraries, PaginationDTO pagination) {
        this.id = id;
        this.itineraries = itineraries;
        this.pagination = pagination;
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

    public PaginationDTO getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }
}
