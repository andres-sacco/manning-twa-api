package com.twa.flights.api.clusters.configuration.settings;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cache")
@Configuration
@EnableCaching
public class CacheConfiguration {
    private int maxSize;
    private int duration;
}
