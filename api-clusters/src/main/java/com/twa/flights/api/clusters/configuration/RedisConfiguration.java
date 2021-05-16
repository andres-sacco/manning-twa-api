package com.twa.flights.api.clusters.configuration;

import com.twa.flights.api.clusters.dto.*;
import com.twa.flights.api.clusters.serializer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.twa.flights.api.clusters.configuration.settings.RedisSettings;

@Configuration
@ConfigurationProperties
public class RedisConfiguration {

    private RedisSettings redis;

    private ClusterSearchSerializer clusterSearchSerializer;

    private CitySerializer citySerializer;

    @Autowired
    public RedisConfiguration(ClusterSearchSerializer clusterSearchSerializer, CitySerializer citySerializer) {
        this.clusterSearchSerializer = clusterSearchSerializer;
        this.citySerializer = citySerializer;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redis.getHost(),
                redis.getPort());
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, ClusterSearchDTO> redisTemplate() {
        RedisTemplate<String, ClusterSearchDTO> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        redisTemplate.setValueSerializer(clusterSearchSerializer);

        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, CityDTO> redisCityTemplate() {
        RedisTemplate<String, CityDTO> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        redisTemplate.setValueSerializer(citySerializer);

        return redisTemplate;
    }

    public RedisSettings getRedis() {
        return redis;
    }

    public void setRedis(RedisSettings redis) {
        this.redis = redis;
    }
}