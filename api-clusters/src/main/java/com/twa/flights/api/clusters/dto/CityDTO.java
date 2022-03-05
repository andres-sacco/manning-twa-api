package com.twa.flights.api.clusters.dto;

import java.util.Objects;

public class CityDTO extends BaseDTO {

    private String timeZone;
    private CountryDTO country;

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CityDTO cityDTO = (CityDTO) o;

        return Objects.equals(getCode(), cityDTO.getCode()) && Objects.equals(getName(), cityDTO.getName())
                && Objects.equals(timeZone, cityDTO.timeZone) && Objects.equals(country, cityDTO.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getName(), timeZone, country);
    }
}
