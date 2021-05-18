package com.twa.flights.api.clusters.serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static com.google.common.base.Charsets.UTF_8;
import static com.twa.flights.api.clusters.helper.CompressionHelper.compress;
import static com.twa.flights.api.clusters.helper.CompressionHelper.decompress;

@Component
public class StringSerializer implements RedisSerializer<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringSerializer.class);

    /**
     * Serialize the given object to binary data.
     *
     * @param string
     *            object to serialize. Can be {@literal null}.
     * @return the equivalent binary data. Can be {@literal null}.
     */
    @Override
    public byte[] serialize(String string) {
        byte[] compressed = null;
        try {
            compressed = compress(string);
        } catch (IOException e) {
            LOGGER.error("Error serializing: {}", e.getMessage());
        }
        return compressed;
    }

    /**
     * Deserialize an object from the given binary data.
     *
     * @param bytes
     *            object binary representation. Can be {@literal null}.
     * @return the equivalent object instance. Can be {@literal null}.
     */
    @Override
    public String deserialize(byte[] bytes) {
        String decompressed = null;
        try {
            decompressed = decompress(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decompressed;
    }

}
