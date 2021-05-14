package com.twa.flights.api.provider.beta.configuration.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Lists;
import com.twa.flights.api.provider.beta.connector.CatalogConnector;
import com.twa.flights.api.provider.beta.settings.CacheSettings;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import static java.util.concurrent.TimeUnit.MINUTES;

@Configuration
@EnableCaching
@AllArgsConstructor
public class CacheManagerConfiguration {
    public static final String CATALOG_CITY = "CATALOG_CITY";
    private final CacheConfiguration cacheConfiguration;
    private final CatalogConnector catalogConnector;

    @Bean
    public CacheManager cacheManager() {
        CacheSettings cacheCitySettings = cacheConfiguration.getCacheSettings(CATALOG_CITY);
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(Lists
                .newArrayList(buildCaffeineCache(CATALOG_CITY, cacheCitySettings, catalogConnector::getCityByCode)));
        return simpleCacheManager;
    }

    public static CaffeineCache buildCaffeineCache(String cacheName, CacheSettings settings,
            Function<String, Object> serviceCall) {
        return new CaffeineCache(cacheName,
                Caffeine.newBuilder().refreshAfterWrite(settings.getRefreshAfterWriteTime(), MINUTES)
                        .expireAfterWrite(settings.getExpireAfterWriteTime(), MINUTES)
                        .maximumSize(settings.getMaxSize()).build(key -> serviceCall.apply(key.toString())));
    }
}
