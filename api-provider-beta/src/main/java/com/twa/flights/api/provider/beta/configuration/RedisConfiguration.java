package com.twa.flights.api.provider.beta.configuration;

import com.twa.flights.api.provider.beta.configuration.settings.RedisSettings;
import com.twa.flights.api.provider.beta.dto.CityDTO;
import com.twa.flights.api.provider.beta.serializer.CitySerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@ConfigurationProperties
public class RedisConfiguration {

    private RedisSettings redis;

    private CitySerializer citySerializer;

    @Autowired
    public RedisConfiguration(CitySerializer citySerializer) {
        this.citySerializer = citySerializer;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redis.getHost(),
                redis.getPort());
        return new JedisConnectionFactory(redisStandaloneConfiguration);
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