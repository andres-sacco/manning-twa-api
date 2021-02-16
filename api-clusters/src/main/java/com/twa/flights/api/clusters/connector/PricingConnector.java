package com.twa.flights.api.clusters.connector;

import java.time.Duration;
import java.util.List;
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

import com.twa.flights.api.clusters.connector.configuration.PricingConnectorConfiguration;
import com.twa.flights.api.clusters.dto.UpdatedPriceInfoDTO;
import com.twa.flights.api.clusters.exception.TWAException;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Component
public class PricingConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(PricingConnector.class);

    private static final String ITINERARIES = "/api/flights/pricing/itineraries";

    private final PricingConnectorConfiguration configuration;

    @Autowired
    public PricingConnector(PricingConnectorConfiguration configuration) {
        this.configuration = configuration;
    }

    public List<UpdatedPriceInfoDTO> priceItineraries(List<ItineraryDTO> itineraries) {
        final long readTimeout = configuration.getReadTimeout();

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Math.toIntExact(configuration.getConnectionTimeout()))
                .responseTimeout(Duration.ofMillis(configuration.getResponseTimeout()))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS)));

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        WebClient client = WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(connector).build();

        return client.post().uri(configuration.getHost().concat(ITINERARIES)).bodyValue(itineraries).retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    LOGGER.error("Error while calling endpoint {} with status code {}", ITINERARIES,
                            clientResponse.statusCode());
                    throw new TWAException("Error while calling pricing endpoint");
                }).toEntityList(UpdatedPriceInfoDTO.class).block().getBody();
    }
}
