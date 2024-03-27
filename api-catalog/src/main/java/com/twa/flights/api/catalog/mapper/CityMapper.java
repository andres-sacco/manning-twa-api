package com.twa.flights.api.catalog.mapper;

import com.twa.flights.api.catalog.dto.CityDTO;
import com.twa.flights.api.catalog.model.City;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface CityMapper extends Converter<City, CityDTO> {

    @Override
    CityDTO convert(City source);

}