package com.twa.flights.api.clusters.dto;

public class UpdatedPriceInfoDTO {

    private String itineraryId;
    private UpdatedPaxPriceDTO adults;
    private UpdatedPaxPriceDTO children;
    private UpdatedPaxPriceDTO infants;

    public String getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(String itineraryId) {
        this.itineraryId = itineraryId;
    }

    public UpdatedPaxPriceDTO getAdults() {
        return adults;
    }

    public void setAdults(UpdatedPaxPriceDTO adults) {
        this.adults = adults;
    }

    public UpdatedPaxPriceDTO getChildren() {
        return children;
    }

    public void setChildren(UpdatedPaxPriceDTO children) {
        this.children = children;
    }

    public UpdatedPaxPriceDTO getInfants() {
        return infants;
    }

    public void setInfants(UpdatedPaxPriceDTO infants) {
        this.infants = infants;
    }
}
