package com.twa.flights.api.provider.beta.configuration.cache;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.google.common.collect.Lists;
import com.twa.flights.api.provider.beta.configuration.settings.CacheSettings;
import com.twa.flights.api.provider.beta.connector.CatalogConnector;
import com.twa.flights.api.provider.beta.serializer.CitySerializer;

@Configuration
@EnableCaching
public class CacheManagerConfiguration {

    public static final String CATALOG_CITY = "CATALOG_CITY";
    

    private final CacheConfiguration cacheConfiguration;
    private final CatalogConnector catalogConnector;
    private final JedisConnectionFactory jedisConnectionFactory;
    private final CitySerializer citySerializer;

    @Autowired
    public CacheManagerConfiguration(final CacheConfiguration cacheConfiguration,
            final CatalogConnector catalogConnector, final JedisConnectionFactory jedisConnectionFactory,
            final CitySerializer citySerializer) {
    	this.cacheConfiguration = cacheConfiguration;
        this.catalogConnector = catalogConnector;
        this.jedisConnectionFactory = jedisConnectionFactory;
        this.citySerializer = citySerializer;
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(Lists
                .newArrayList(RedisCacheManager.builder(jedisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration()).build().getCache(CATALOG_CITY)));
        
        return simpleCacheManager;
    }

    private RedisCacheConfiguration redisCacheConfiguration() {
        CacheSettings cacheCitySettings = cacheConfiguration.getCacheSettings(CATALOG_CITY);
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(citySerializer))
                .entryTtl(Duration.ofMinutes(cacheCitySettings.getExpireAfterWriteTime()));
    }
}
