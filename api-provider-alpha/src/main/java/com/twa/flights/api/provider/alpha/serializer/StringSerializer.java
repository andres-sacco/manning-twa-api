package com.twa.flights.api.provider.alpha.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class StringSerializer implements RedisSerializer<String> {

   @Override
   public byte[] serialize(String s) throws SerializationException {
      if(s == null)
         return new byte[]{};

      return s.getBytes(StandardCharsets.UTF_8);
   }

   @Override
   public String deserialize(byte[] bytes) throws SerializationException {
      return new String(bytes);
   }
}
