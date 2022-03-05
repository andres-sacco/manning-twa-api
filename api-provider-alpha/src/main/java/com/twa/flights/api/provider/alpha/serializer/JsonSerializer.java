package com.twa.flights.api.provider.alpha.serializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.twa.flights.api.provider.alpha.serializer.compressor.GzipCompressor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class JsonSerializer {

   private static final Logger LOGGER = LoggerFactory.getLogger(JsonSerializer.class);
   private static final ObjectMapper OBJECT_MAPPER;

   private JsonSerializer() {
      // just to avoid create instances
   }

   static {
      OBJECT_MAPPER = new ObjectMapper().configure(MapperFeature.USE_GETTERS_AS_SETTERS, false)
              .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
              .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE).registerModule(new JavaTimeModule());
   }

   public static byte[] serialize(Object object) {
      byte[] serializedObject = null;

      try {
         var jsonObject = OBJECT_MAPPER.writeValueAsString(object);
         var compressedJsonObject = GzipCompressor.compress(jsonObject);
         serializedObject = compressedJsonObject.getBytes();
      } catch (Exception e) {
         LOGGER.error("Error serializing object: {}", e.getMessage());
      }

      return serializedObject;
   }

   public static <T> T deserialize(byte[] raw, Class<T> reference) {
      if (raw == null)
         return null;

      T deserializedObject = null;
      try {
         var rawString = new String(raw, StandardCharsets.UTF_8);
         var compressedJsonObject = GzipCompressor.decompress(rawString);
         deserializedObject = OBJECT_MAPPER.readValue(compressedJsonObject, reference);
      } catch (Exception e) {
         LOGGER.error("Can't deserialize object: {}", e.getMessage());
      }
      return deserializedObject;
   }

}
