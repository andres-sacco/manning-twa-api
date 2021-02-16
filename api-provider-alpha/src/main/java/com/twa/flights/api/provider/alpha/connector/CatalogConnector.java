package com.twa.flights.api.provider.alpha.connector;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.twa.flights.api.provider.alpha.connector.configuration.CatalogConnectorConfiguration;
import com.twa.flights.api.provider.alpha.dto.CityDTO;
import com.twa.flights.api.provider.alpha.exception.TWAException;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Component
public class CatalogConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogConnector.class);

    private static final String GET_CITY_BY_CODE = "/api/flights/catalog/city/";

    private final CatalogConnectorConfiguration configuration;

    @Autowired
    public CatalogConnector(CatalogConnectorConfiguration configuration) {
        this.configuration = configuration;
    }

    public CityDTO getCityByCode(String code) {
        final long readTimeout = configuration.getReadTimeout();

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Math.toIntExact(configuration.getConnectionTimeout()))
                .responseTimeout(Duration.ofMillis(configuration.getResponseTimeout()))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS)));

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        WebClient client = WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(connector).build();

        return client.get().uri(configuration.getHost().concat(GET_CITY_BY_CODE).concat(code)).retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    LOGGER.error("Error while calling endpoint {} with status code {}", GET_CITY_BY_CODE,
                            clientResponse.statusCode());
                    throw new TWAException("Error while calling catalog endpoint");
                }).bodyToMono(CityDTO.class).block();
    }
}
