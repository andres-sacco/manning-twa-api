package com.twa.flights.api.provider.beta.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.*;

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
