package com.twa.flights.api.clusters.compressor;

import com.twa.flights.api.clusters.dto.CityDTO;
import com.twa.flights.api.clusters.dto.CountryDTO;
import com.twa.flights.api.clusters.serializer.CitySerializer;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CitySerializerTest {

    static final String SERIALIZED_CITY_DTO = "483473494141414141414141414b7457796b764d54565779556e49715463334c4c315a777a43784b4c5662535555724f5477474a4f6762704f6746354a5a6d35716646562b586b67495866664546316a73497253764a4b6953695772617067526a6b587071586b6c6d586d4a79507242624a416f5545724a4b7138304a36653246674271776776776451414141413d3d";

    CitySerializer citySerializer = new CitySerializer();
    CountryDTO countryDTO = new CountryDTO();
    CityDTO cityDTO = new CityDTO();

    @BeforeAll
    void arrange() {
        countryDTO.setCode("AR");
        countryDTO.setName("Argentina");

        cityDTO.setCode("AR-B");
        cityDTO.setName("Buenos Aires");
        cityDTO.setTimeZone("GMT-3");
        cityDTO.setCountry(countryDTO);
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
