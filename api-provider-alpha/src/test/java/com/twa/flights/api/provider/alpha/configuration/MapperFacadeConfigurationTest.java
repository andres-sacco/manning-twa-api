package com.twa.flights.api.provider.alpha.configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class MapperFacadeConfigurationTest {

    @Test
    public void should_return_not_null_mapper_facade() {
        MapperFacadeConfiguration mapperConfiguration = new MapperFacadeConfiguration();
        assertNotNull(mapperConfiguration.mapperFacade());
    }
}
