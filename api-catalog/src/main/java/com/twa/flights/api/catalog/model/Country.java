package com.twa.flights.api.catalog.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Country extends Base {

    @JoinColumn(name = "continent_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Continent continent;

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
