package com.twa.flights.api.catalog.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class City extends Base {

    @Column(length = 50, nullable = false)
    private String timeZone;

    @JoinColumn(name = "country_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Country country;

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
