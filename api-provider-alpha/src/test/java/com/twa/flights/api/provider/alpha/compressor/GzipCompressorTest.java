package com.twa.flights.api.provider.alpha.compressor;

import com.twa.flights.api.provider.alpha.serializer.compressor.GzipCompressor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GzipCompressorTest {

    public static final String COMPRESSED_PAYLOAD = "H4sIAAAAAAAAAHNJTc7PLShKLS5OTVEoSKzMyU9MAQAtEgWTFAAAAA==";
    public static final String DECOMPRESSED_PAYLOAD = "Decompressed payload";

    @Test
    void givenPayloadWhenCompressionIsAppliedPayloadShouldBeCompressed() throws Exception {
        // Act
        var result = GzipCompressor.compress(DECOMPRESSED_PAYLOAD);

        // Assert
        assertEquals(COMPRESSED_PAYLOAD, result);
    }

    @Test
    void givenCompressedPayloadWhenDecompressionIsAppliedPayloadShouldBeDecompressed() throws Exception {
        // Act
        var result = GzipCompressor.decompress(COMPRESSED_PAYLOAD);

        // Assert
        assertEquals(DECOMPRESSED_PAYLOAD, result);
    }
}
