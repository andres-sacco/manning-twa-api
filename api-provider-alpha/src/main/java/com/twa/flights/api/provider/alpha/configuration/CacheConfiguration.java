package com.twa.flights.api.provider.alpha.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.Configuration;

@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cache")
@Configuration
public class CacheConfiguration {
    private int maxSize;
    private int duration;
}
