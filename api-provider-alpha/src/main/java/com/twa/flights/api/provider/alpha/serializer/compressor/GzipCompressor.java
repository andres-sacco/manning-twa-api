package com.twa.flights.api.provider.alpha.serializer.compressor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static java.lang.String.format;

public interface GzipCompressor {

   Logger LOGGER = LoggerFactory.getLogger(GzipCompressor.class);

   static String compress(String str) throws CompressionException {
      LOGGER.debug(format("Original String Length : %s", str.length()));

      try (var baos = new ByteArrayOutputStream();
           var gos = new GZIPOutputStream(baos);) {
         gos.write(str.getBytes(StandardCharsets.UTF_8));
         gos.close();

         var base64Encoded = Base64.getEncoder().encodeToString(baos.toByteArray());
         LOGGER.debug(format("Compressed String length : %s", base64Encoded.length()));

         return base64Encoded;
      } catch (Exception ex) {
         throw new CompressionException(ex.getMessage(), ex.getCause());
      }
   }

   static String decompress(String str) throws CompressionException {
      try (var gis = new GZIPInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(str)));
           var bf = new BufferedReader(new InputStreamReader(gis, StandardCharsets.UTF_8));) {
         var outStr = new StringBuilder();
         String line = null;
         while ((line = bf.readLine()) != null) {
            outStr.append(line);
         }
         LOGGER.debug(format("Decompressed String length : %s", outStr.length()));

         return outStr.toString();
      } catch (Exception ex) {
         throw new CompressionException(ex.getMessage(), ex.getCause());
      }
   }

}