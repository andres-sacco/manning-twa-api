package com.twa.flights.api.catalog.configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.twa.flights.api.catalog.service.AppInfoService;

class SwaggerConfigurationTest {

    AppInfoService appInfoService;

    @BeforeEach
    public void setUp() {
        appInfoService = mock(AppInfoService.class);
    }

    @Test
    void should_return_information_of_default_api() {
        SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration(appInfoService);
        assertNotNull(swaggerConfiguration.defaultApi());
    }

    @Test
    void should_return_information_of_openapi() {
        SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration(appInfoService);
        assertNotNull(swaggerConfiguration.openAPI());
    }
}
