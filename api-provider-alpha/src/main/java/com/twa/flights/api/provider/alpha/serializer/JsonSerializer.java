package com.twa.flights.api.provider.alpha.serializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@NoArgsConstructor
public class JsonSerializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonSerializer.class);
    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper().configure(MapperFeature.USE_GETTERS_AS_SETTERS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE).registerModule(new JavaTimeModule());
    }

    public static byte[] serialize(Object object) {
        byte[] json = null;
        try {
            json = MAPPER.writeValueAsString(object).getBytes();
        } catch (IOException e) {
            LOGGER.error("Error serializing object: {}", e.getMessage());
        }
        return json;
    }

    public static <T> T deserialize(byte[] raw, Class<T> reference) {
        if (raw == null)
            return null;

        T object = null;
        try {
            object = MAPPER.readValue(raw, reference);
        } catch (IOException e) {
            LOGGER.error("Can't deserialize object: {}", e.getMessage());
        }
        return object;
    }
}
