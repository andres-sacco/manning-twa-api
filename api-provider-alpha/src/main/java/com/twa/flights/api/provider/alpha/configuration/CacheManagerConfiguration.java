package com.twa.flights.api.provider.alpha.configuration;

import com.google.common.collect.Lists;
import com.twa.flights.api.provider.alpha.configuration.settings.CacheSettings;
import com.twa.flights.api.provider.alpha.serializer.CitySerializer;
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
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(CacheSettings.class)
@EnableCaching
public class CacheManagerConfiguration {

    private final String CATALOG_CITY = "cities";

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
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(Lists.newArrayList(RedisCacheManager.builder(jedisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration()).build().getCache(CATALOG_CITY)));

        return simpleCacheManager;
    }

    private RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(citySerializer))
                .entryTtl(Duration.ofMinutes(cacheSettings.getExpireAfterWriteTime()));
    }

}