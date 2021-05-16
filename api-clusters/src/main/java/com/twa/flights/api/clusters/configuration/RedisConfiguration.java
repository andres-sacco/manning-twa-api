package com.twa.flights.api.clusters.configuration;

import com.twa.flights.api.clusters.dto.CityDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.twa.flights.api.clusters.configuration.settings.RedisSettings;
import com.twa.flights.api.clusters.dto.ClusterSearchDTO;
import com.twa.flights.api.clusters.serializer.ClusterSearchSerializer;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties // (prefix = "redis")
public class RedisConfiguration {
    private Map<String, RedisSettings> redis;

    private ClusterSearchSerializer clusterSearchSerializer;

    @Autowired
    public RedisConfiguration(ClusterSearchSerializer clusterSearchSerializer) {
        this.clusterSearchSerializer = clusterSearchSerializer;
    }

    // Cache to save all the itineraries in a database
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisSettings settings = redis.get("db");
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(settings.getHost(),
                settings.getPort());
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, ClusterSearchDTO> redisTemplate() {
        RedisTemplate<String, ClusterSearchDTO> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        redisTemplate.setValueSerializer(clusterSearchSerializer);

        return redisTemplate;
    }

    // External cache to save the request to catalog
    @Bean
    public JedisConnectionFactory catalogJedisConnectionFactory() {
        RedisSettings settings = redis.get("remote-cache");
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(settings.getHost(),
                settings.getPort());
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, CityDTO> catalogRedisTemplate() {
        RedisTemplate<String, CityDTO> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(catalogJedisConnectionFactory());

        return redisTemplate;
    }
}