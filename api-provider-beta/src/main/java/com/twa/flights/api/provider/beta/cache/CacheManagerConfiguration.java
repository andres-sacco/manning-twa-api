package com.twa.flights.api.provider.beta.cache;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheManagerConfiguration extends CachingConfigurerSupport {

    @Autowired
    CacheConfiguration cacheConfiguration;

    @Bean
    @Override
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheNames(Arrays.asList("cityCache"));
        cacheManager.setAllowNullValues(false);
        cacheManager.setCacheSpecification(cacheConfiguration.toSpec());

        return cacheManager;

    }

}
