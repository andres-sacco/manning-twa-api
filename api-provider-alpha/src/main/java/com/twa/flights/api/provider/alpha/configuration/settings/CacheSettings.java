package com.twa.flights.api.provider.alpha.configuration.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheSettings {
    private static final Integer DEFAULT_EXPIRE_AFTER = 15;
    public static final CacheSettings DEFAULT_CACHE_SETTINGS = new CacheSettings(DEFAULT_EXPIRE_AFTER);

    private Integer expireAfterWriteTime;
}