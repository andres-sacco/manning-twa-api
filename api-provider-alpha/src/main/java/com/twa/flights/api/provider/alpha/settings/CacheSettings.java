package com.twa.flights.api.provider.alpha.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheSettings {
    private static final Integer DEFAULT_REFRESH_AFTER = 10;
    private static final Integer DEFAULT_EXPIRE_AFTER = 15;
    private static final Integer DEFAULT_MAX_SIZE = 180;
    public static final CacheSettings DEFAULT_CACHE_SETTINGS = new CacheSettings(DEFAULT_REFRESH_AFTER,
            DEFAULT_EXPIRE_AFTER, DEFAULT_MAX_SIZE);

    private Integer refreshAfterWriteTime;
    private Integer expireAfterWriteTime;
    private Integer maxSize;
}