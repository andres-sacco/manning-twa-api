package com.twa.flights.api.provider.alpha.compressor;

import com.twa.flights.api.provider.alpha.dto.CityDTO;
import com.twa.flights.api.provider.alpha.serializer.CitySerializer;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CitySerializerTest {

    static final String SERIALIZED_CITY_DTO = "483473494141414141414141414b7457796b764d54565779556e49715463334c4c315a777a43784b4c5662535555724f5477474a4f6762704f6746354a5a6d35716646562b586b67495866664546316a70566f417338493452546b414141413d";

    CitySerializer citySerializer = new CitySerializer();
    CityDTO cityDTO = new CityDTO();

    @BeforeAll
    void arrange() {
        cityDTO.setCode("AR-B");
        cityDTO.setName("Buenos Aires");
        cityDTO.setTimeZone("GMT-3");
    }

    @Test
    void givenCityDTOWhenSerializationIsAppliedThenObjectShouldBeSerialized() {
        // Act
        var result = citySerializer.serialize(cityDTO);

        // Assert
        assertEquals(SERIALIZED_CITY_DTO, Hex.encodeHexString(result));
    }

    @Test
    void givenSerializedCityDTOWhenDeserializationIsAppliedThenObjectShouldBeDeserialized() throws Exception {
        // Act
        var result = citySerializer.deserialize(Hex.decodeHex(SERIALIZED_CITY_DTO));

        // Assert
        assertEquals(cityDTO, result);
    }
}
