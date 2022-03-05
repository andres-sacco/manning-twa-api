package com.twa.flights.api.provider.alpha.compressor;

import com.twa.flights.api.provider.alpha.serializer.compressor.GzipCompressor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GzipCompressorTest {

   @Test
   void givenPayloadWhenCompressionIsAppliedPayloadShouldBeCompressed() throws Exception {
      // Arrange
      var payload = "Decompressed payload";
      var compressedPayload = "H4sIAAAAAAAAAHNJTc7PLShKLS5OTVEoSKzMyU9MAQAtEgWTFAAAAA==";

      // Act
      var result = GzipCompressor.compress(payload);

      // Assert
      assertEquals(compressedPayload, result);
   }

   @Test
   void givenCompressedPayloadWhenDecompressionIsAppliedPayloadShouldBeDecompressed() throws Exception {
      // Arrange
      var compressedPayload = "H4sIAAAAAAAAAHNJTc7PLShKLS5OTVEoSKzMyU9MAQAtEgWTFAAAAA==";
      var decompressedPayload = "Decompressed payload";

      // Act
      var result = GzipCompressor.decompress(compressedPayload);

      // Assert
      assertEquals(decompressedPayload, result);
   }
}
