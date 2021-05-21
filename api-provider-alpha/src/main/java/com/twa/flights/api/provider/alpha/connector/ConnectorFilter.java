package com.twa.flights.api.provider.alpha.connector;

import org.slf4j.Logger;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

import reactor.core.publisher.Mono;

public class ConnectorFilter {

    public static ExchangeFilterFunction logRequest(Logger logger) {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            if (logger.isInfoEnabled()) {
                StringBuilder sb = new StringBuilder("Request: \n");
                sb.append("\t URL: " + clientRequest.url() + "\n");
                sb.append("\t Method: " + clientRequest.method() + "\n");

                clientRequest.headers().forEach((name, values) -> values
                        .forEach(value -> sb.append("\t Headers: " + name + "=" + value + "\n")));
                logger.info(sb.toString());
            }
            return Mono.just(clientRequest);
        });
    }

    public static ExchangeFilterFunction logResponse(Logger logger) {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (logger.isInfoEnabled()) {
                StringBuilder sb = new StringBuilder("\t Response: \n");

                clientResponse.headers().asHttpHeaders().forEach((name, values) -> values
                        .forEach(value -> sb.append("\t Headers: " + name + "=" + value + "\n")));
                logger.info(sb.toString());
            }
            return Mono.just(clientResponse);
        });
    }

}
