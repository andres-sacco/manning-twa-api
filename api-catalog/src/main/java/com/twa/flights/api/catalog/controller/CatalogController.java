package com.twa.flights.api.catalog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twa.flights.api.catalog.controller.documentation.CatalogResources;
import com.twa.flights.api.catalog.dto.CityDTO;
import com.twa.flights.api.catalog.service.CityService;

@RestController
@RequestMapping("/")
public class CatalogController implements CatalogResources {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogController.class);

    private CityService entityService;

    @Autowired
    public CatalogController(CityService entityService) {
        this.entityService = entityService;
    }

    @Override
    public ResponseEntity<CityDTO> getCityByCode(String code) {
        LOGGER.info("Obtain all the information about the city with code {}", code);

        CityDTO response = entityService.getCityByCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
