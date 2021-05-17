package com.twa.flights.api.clusters.connector.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

public class ConnectorFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectorFilter.class);

    private ConnectorFilter() {
    }

    public static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(request -> {
            LOGGER.info("********** Request **********");
            LOGGER.info("method: " + request.method().name());
            LOGGER.info("url: " + request.url());
            request.headers().forEach((name, values) -> {
                values.forEach(value -> {
                    LOGGER.info("{}: {}", name, value);
                });
            });

            return Mono.just(request);
        });
    }

    public static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(response -> {
            LOGGER.info("********** Response **********");
            HttpStatus status = response.statusCode();
            LOGGER.debug("Status {} ({})", status.value(), status.getReasonPhrase());
            response.headers().asHttpHeaders().forEach((name, values) -> {
                values.forEach(value -> {
                    LOGGER.info("{}: {}", name, value);
                });
            });

            return logBody(response);
        });
    }

    private static Mono<ClientResponse> logBody(ClientResponse response) {
        if (response.statusCode().is4xxClientError() || response.statusCode().is5xxServerError()) {
            return response.bodyToMono(String.class).flatMap(body -> {
                LOGGER.debug("Body is {}", body);
                return Mono.just(response);
            });
        } else {
            return Mono.just(response);
        }
    }
}