package com.twa.flights.api.catalog.configuration;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.util.Collections;
import java.util.Map;

@Configuration
public class InfoEndpointConfiguration {

    private static final String APP_INFO_FILE = "app-info.yml";

    @Bean
    @SuppressWarnings("unchecked")
    public InfoEndpoint infoEndpoint() {
        Yaml yaml = new Yaml();
        Map<String, Object> appInfo = yaml.loadAs(this.getClass().getClassLoader().getResourceAsStream(APP_INFO_FILE),
                Map.class);

        InfoContributor ic = builder -> builder.withDetails(appInfo);
        return new InfoEndpoint(Collections.singletonList(ic));
    }
}