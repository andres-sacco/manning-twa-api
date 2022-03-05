package com.twa.flights.api.provider.alpha.dto;

import java.util.Objects;

public class CityDTO extends BaseDTO {

    private String timeZone;

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var cityDTO = (CityDTO) o;
        return  Objects.equals(getCode(), cityDTO.getCode()) &&
                Objects.equals(getName(), cityDTO.getName()) &&
                Objects.equals(timeZone, cityDTO.timeZone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getName(), timeZone);
    }

}
