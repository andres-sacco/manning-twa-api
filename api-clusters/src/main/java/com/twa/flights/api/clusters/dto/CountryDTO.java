package com.twa.flights.api.clusters.dto;

public class CountryDTO extends BaseDTO {

    private ContinentDTO continent;

    public ContinentDTO getContinent() {
        return continent;
    }

    public void setContinent(ContinentDTO continent) {
        this.continent = continent;
    }
}
