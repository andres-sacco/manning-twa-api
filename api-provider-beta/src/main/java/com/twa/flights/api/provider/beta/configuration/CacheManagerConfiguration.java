package com.twa.flights.api.provider.beta.configuration;

import com.google.common.collect.Lists;
import com.twa.flights.api.provider.beta.configuration.settings.CacheSettings;
import com.twa.flights.api.provider.beta.serializer.CitySerializer;
import com.twa.flights.api.provider.beta.serializer.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(CacheSettings.class)
@EnableCaching
public class CacheManagerConfiguration {

    private static final String CATALOG_CITY = "cities";

    private final CacheSettings cacheSettings;
    private final JedisConnectionFactory jedisConnectionFactory;
    private final CitySerializer citySerializer;

    public CacheManagerConfiguration(CacheSettings cacheSettings, JedisConnectionFactory jedisConnectionFactory,
            CitySerializer citySerializer) {
        this.cacheSettings = cacheSettings;
        this.jedisConnectionFactory = jedisConnectionFactory;
        this.citySerializer = citySerializer;
    }

    @Bean
    public CacheManager cacheManager() {
        var simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(Lists.newArrayList(RedisCacheManager.builder(jedisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration()).build().getCache(CATALOG_CITY)));

        return simpleCacheManager;
    }

    private RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(citySerializer))
                .entryTtl(Duration.ofMinutes(cacheSettings.getExpireAfterWriteTime()));
    }

}