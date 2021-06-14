package com.twa.flights.api.catalog.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.twa.flights.api.catalog.dto.CityDTO;
import com.twa.flights.api.catalog.service.CityService;

@ExtendWith(MockitoExtension.class)
public class CatalogControllerTest {

    private static final String DEFAULT_CITY_CODE = "BUE";

    private CityService entityService;
    private CatalogController controller;

    @BeforeEach
    public void setUp() {
        entityService = mock(CityService.class);
        controller = new CatalogController(entityService);
    }

    @Test
    public void should_return_an_city() {
        CityDTO city = new CityDTO();
        city.setCode(DEFAULT_CITY_CODE);

        when(entityService.getCityByCode(DEFAULT_CITY_CODE)).thenReturn(city);
        ResponseEntity<CityDTO> response = controller.getCityByCode(DEFAULT_CITY_CODE);

        assertAll(() -> assertNotNull(response), () -> assertEquals(200, response.getStatusCodeValue()),
                () -> assertEquals(DEFAULT_CITY_CODE, response.getBody().getCode()));
    }
}
