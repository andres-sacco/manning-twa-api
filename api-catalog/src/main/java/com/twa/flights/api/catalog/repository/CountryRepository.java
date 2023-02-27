package com.twa.flights.api.catalog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.twa.flights.api.catalog.model.Country;

@Repository
public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {

    Country findByCode(String code);
}
