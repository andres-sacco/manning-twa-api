package com.twa.flights.api.provider.beta.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
@EnableConfigurationProperties(CacheConfiguration.class)
public class CacheManagerConfiguration {

    private final CacheConfiguration cacheConfiguration;

    public CacheManagerConfiguration(CacheConfiguration cacheConfiguration) {
        this.cacheConfiguration = cacheConfiguration;
    }

    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(cacheConfiguration.getDuration(), TimeUnit.MINUTES)
                .maximumSize(cacheConfiguration.getMaximumSize());
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        var caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        caffeineCacheManager.setCacheNames(List.of("cities"));
        caffeineCacheManager.setAllowNullValues(false);

        return caffeineCacheManager;
    }
}
