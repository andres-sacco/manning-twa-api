package com.twa.flights.api.clusters.configuration;

import com.google.common.collect.Lists;
import com.twa.flights.api.clusters.configuration.settings.*;
import com.twa.flights.api.clusters.serializer.CitySerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@EnableCaching
@Configuration
public class CacheManagerConfiguration {
    public static final String CATALOG_CITY = "catalog-city";

    private final JedisConnectionFactory jedisConnectionFactory;
    private final CitySerializer citySerializer;
    private final CacheConfiguration cacheConfiguration;

    @Autowired
    public CacheManagerConfiguration(JedisConnectionFactory jedisConnectionFactory, CitySerializer citySerializer,
            CacheConfiguration cacheConfiguration) {
        this.jedisConnectionFactory = jedisConnectionFactory;
        this.citySerializer = citySerializer;
        this.cacheConfiguration = cacheConfiguration;
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(Lists.newArrayList(RedisCacheManager.builder(jedisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration()).build().getCache(CATALOG_CITY)));

        return simpleCacheManager;
    }

    private RedisCacheConfiguration redisCacheConfiguration() {
        CacheSettings cacheCitySettings = cacheConfiguration.getCacheSettings(CATALOG_CITY);
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(citySerializer))
                .entryTtl(Duration.ofMinutes(cacheCitySettings.getExpireAfterWriteTime()));
    }
}
