package com.twa.flights.api.clusters.configuration.settings;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cache.catalog-city")
@Configuration
public class CacheSettings {
    private int expireAfterWriteTime;
}
