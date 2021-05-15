package com.twa.flights.api.clusters.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.twa.flights.api.clusters.configuration.settings.CacheConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheManagerConfiguration {
    @Bean
    public Caffeine caffeineConfig(CacheConfiguration configuration) {
        return Caffeine.newBuilder().expireAfterWrite(configuration.getDuration(), TimeUnit.MINUTES)
                .maximumSize(configuration.getMaxSize());
    }

    @Bean
    public CaffeineCacheManager cacheManager(Caffeine caffeineConfig) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("catalog");
        cacheManager.setCaffeine(caffeineConfig);
        return cacheManager;
    }
}
