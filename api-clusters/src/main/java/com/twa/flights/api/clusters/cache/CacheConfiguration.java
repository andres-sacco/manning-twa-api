package com.twa.flights.api.clusters.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {
    @Value("${cache.maxSize}")
    long maxSize;

    @Value("${cache.timeout}")
    long timeout;

    public String toSpec() {
        return "maximumSize=" + maxSize + ",expireAfterAccess=" + timeout + "s";
    }
}
