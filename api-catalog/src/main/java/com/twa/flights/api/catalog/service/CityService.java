package com.twa.flights.api.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twa.flights.api.catalog.dto.CityDTO;
import com.twa.flights.api.catalog.model.City;
import com.twa.flights.api.catalog.repository.CityRepository;

import ma.glasnost.orika.MapperFacade;

@Service
public class CityService {

    CityRepository cityRepository;
    MapperFacade mapper;

    @Autowired
    public CityService(CityRepository cityRepository, MapperFacade mapper) {
        this.cityRepository = cityRepository;
        this.mapper = mapper;
    }

    public CityDTO getCityByCode(String code) {
        City city = cityRepository.findByCode(code);
        return mapper.map(city, CityDTO.class);
    }
}
