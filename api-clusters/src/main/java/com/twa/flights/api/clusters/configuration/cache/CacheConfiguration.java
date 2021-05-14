package com.twa.flights.api.clusters.configuration.cache;

import com.twa.flights.api.clusters.configuration.settings.CacheSettings;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.Map;
import static com.twa.flights.api.clusters.configuration.settings.CacheSettings.DEFAULT_CACHE_SETTINGS;

@Data
@Configuration
@ConfigurationProperties("cache")
public class CacheConfiguration {
    private Map<String, CacheSettings> configuration;

    public CacheSettings getCacheSettings(final String cacheName) {
        return configuration.getOrDefault(cacheName, DEFAULT_CACHE_SETTINGS);
    }
}
