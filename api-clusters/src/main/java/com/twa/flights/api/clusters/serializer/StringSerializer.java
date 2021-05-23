package com.twa.flights.api.clusters.serializer;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class StringSerializer implements RedisSerializer<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringSerializer.class);

    private StringSerializer() {
        // just to avoid create instances
    }

    @Override
    public byte[] serialize(String str) {
        LOGGER.info("Original String Length : " + str.length());
        ByteArrayOutputStream obj = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gzip = new GZIPOutputStream(obj);
            gzip.write(str.getBytes("UTF-8"));
            gzip.close();
        } catch (IOException e) {
            throw new RuntimeException("String Compression failed!", e);
        }
        String base64Encoded = Base64.getEncoder().encodeToString(obj.toByteArray());

        LOGGER.info("Compressed String length : " + base64Encoded.length());
        return base64Encoded.getBytes();
    }

    @Override
    public String deserialize(byte[] raw) {
        String outStr = "";
        String line;
        try {
            GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(raw));
            BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
            while ((line = bf.readLine()) != null) {
                outStr += line;
            }
        } catch (IOException e) {
            throw new RuntimeException("String Decompression failed!", e);
        }

        LOGGER.debug("Decompressed String length : " + outStr.length());
        return outStr;
    }

}
