package com.twa.flights.api.provider.beta.configuration.settings;

import lombok.Data;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cache")
@Configuration
public class CacheConfiguration {
    private Map<String, CacheSettings> settings;

    public CacheSettings getCacheSettings(String settings) {
        return this.settings.get(settings);
    }

    public Map<String, CacheSettings> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, CacheSettings> settings) {
        this.settings = settings;
    }
}
