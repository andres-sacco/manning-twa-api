package com.twa.flights.api.provider.beta.configuration.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "redis")
@ConstructorBinding
public class RedisSettings {

    private final String host;
    private final int port;

    public RedisSettings(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
