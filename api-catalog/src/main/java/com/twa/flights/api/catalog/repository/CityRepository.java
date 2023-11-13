package com.twa.flights.api.catalog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.twa.flights.api.catalog.model.City;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Long> {

    City findByCode(String code);

}
