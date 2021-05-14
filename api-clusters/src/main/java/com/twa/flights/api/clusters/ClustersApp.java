package com.twa.flights.api.clusters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = { "com.twa.flights.api" })
public class ClustersApp implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ClustersApp.class, args);
    }
}
