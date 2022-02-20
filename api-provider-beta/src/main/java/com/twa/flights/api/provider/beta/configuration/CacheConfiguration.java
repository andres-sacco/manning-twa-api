package com.twa.flights.api.provider.beta.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "cache")
@ConstructorBinding
public class CacheConfiguration {

    private final long maximumSize;
    private final long duration;

    public CacheConfiguration(long maximumSize, long duration) {
        this.maximumSize = maximumSize;
        this.duration = duration;
    }

    public long getMaximumSize() {
        return maximumSize;
    }

    public long getDuration() {
        return duration;
    }
}
