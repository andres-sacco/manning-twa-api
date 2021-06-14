package com.twa.flights.api.catalog.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
