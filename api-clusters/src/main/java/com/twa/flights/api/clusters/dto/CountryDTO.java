package com.twa.flights.api.clusters.dto;

import java.util.Objects;

public class CountryDTO extends BaseDTO {

    private ContinentDTO continent;

    public ContinentDTO getContinent() {
        return continent;
    }

    public void setContinent(ContinentDTO continent) {
        this.continent = continent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CountryDTO that = (CountryDTO) o;

        return Objects.equals(getCode(), that.getCode()) && Objects.equals(getName(), that.getName())
                && Objects.equals(continent, that.continent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getName(), continent);
    }
}
