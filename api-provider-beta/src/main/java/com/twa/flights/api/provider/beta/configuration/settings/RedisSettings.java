package com.twa.flights.api.provider.beta.configuration.settings;

import lombok.Data;

@Data
public class RedisSettings {
    private String host;
    private int port;
}
