package com.twa.flights.api.clusters.configuration;

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

@Configuration
@ConfigurationProperties
public class RedisConfiguration {

    private RedisSettings redis;

    private ClusterSearchSerializer clusterSearchSerializer;

    @Autowired
    public RedisConfiguration(ClusterSearchSerializer clusterSearchSerializer) {
        this.clusterSearchSerializer = clusterSearchSerializer;
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

    public RedisSettings getRedis() {
        return redis;
    }

    public void setRedis(RedisSettings redis) {
        this.redis = redis;
    }
}