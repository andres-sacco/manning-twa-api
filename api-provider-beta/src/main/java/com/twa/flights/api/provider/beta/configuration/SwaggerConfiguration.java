package com.twa.flights.api.provider.beta.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.twa.flights.api.provider.beta.service.AppInfoService;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

    AppInfoService appInfoService;

    @Autowired
    public SwaggerConfiguration(AppInfoService appInfoService) {
        this.appInfoService = appInfoService;
    }

    @Bean
    public GroupedOpenApi defaultApi() {
        return GroupedOpenApi.builder().group("default").packagesToScan("com.twa.flights.api.provider.beta.controller")
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("API Provider Beta")
                .description("This is the documentation of the API which obtain the info of provider Beta")
                .version(appInfoService.getVersion()));
    }
}