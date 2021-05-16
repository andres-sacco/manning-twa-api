package com.twa.flights.api.provider.beta.configuration.cache;

import com.twa.flights.api.provider.beta.configuration.settings.CacheSettings;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties("cache")
public class CacheConfiguration {
    private Map<String, CacheSettings> configuration;

    public CacheSettings getCacheSettings(final String cacheName) {
        return configuration.getOrDefault(cacheName, CacheSettings.DEFAULT_CACHE_SETTINGS);
    }

    public Map<String, CacheSettings> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Map<String, CacheSettings> configuration) {
        this.configuration = configuration;
    }
}
