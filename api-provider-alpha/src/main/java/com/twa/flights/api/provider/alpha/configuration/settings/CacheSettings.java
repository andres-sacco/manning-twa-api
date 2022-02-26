package com.twa.flights.api.provider.alpha.configuration.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "cache")
@ConstructorBinding
public class CacheSettings {
    private final long expireAfterWriteTime;

    public CacheSettings(long expireAfterWriteTime) {
        this.expireAfterWriteTime = expireAfterWriteTime;
    }

    public long getExpireAfterWriteTime() {
        return expireAfterWriteTime;
    }
}
