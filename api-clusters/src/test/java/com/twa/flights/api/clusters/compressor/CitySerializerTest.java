package com.twa.flights.api.clusters.compressor;

import com.twa.flights.api.clusters.dto.CityDTO;
import com.twa.flights.api.clusters.dto.CountryDTO;
import com.twa.flights.api.clusters.serializer.CitySerializer;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CitySerializerTest {

    CitySerializer citySerializer = new CitySerializer();

    @Test
    void givenCityDTOWhenSerializationIsAppliedThenObjectShouldBeSerialized() {
        // Arrange
        var countryDTO = new CountryDTO();
        countryDTO.setCode("AR");
        countryDTO.setName("Argentina");

        var cityDTO = new CityDTO();
        cityDTO.setCode("AR-B");
        cityDTO.setName("Buenos Aires");
        cityDTO.setTimeZone("GMT-3");
        cityDTO.setCountry(countryDTO);

        var serializedCityDTO = "483473494141414141414141414b7457796b764d54565779556e49715463334c4c315a777a43784b4c5662535555724f5477474a4f6762704f6746354a5a6d35716646562b586b67495866664546316a73497253764a4b6953695772617067526a6b587071586b6c6d586d4a79507242624a416f5545724a4b7138304a36653246674271776776776451414141413d3d";

        // Act
        var result = citySerializer.serialize(cityDTO);

        // Assert
        assertEquals(serializedCityDTO, Hex.encodeHexString(result));
    }

    @Test
    void givenSerializedCityDTOWhenDeserializationIsAppliedThenObjectShouldBeDeserialized() throws Exception {
        // Arrange
        var countryDTO = new CountryDTO();
        countryDTO.setCode("AR");
        countryDTO.setName("Argentina");

        var cityDTO = new CityDTO();
        cityDTO.setCode("AR-B");
        cityDTO.setName("Buenos Aires");
        cityDTO.setTimeZone("GMT-3");
        cityDTO.setCountry(countryDTO);

        var serializedCityDTO = "483473494141414141414141414b7457796b764d54565779556e49715463334c4c315a777a43784b4c5662535555724f5477474a4f6762704f6746354a5a6d35716646562b586b67495866664546316a73497253764a4b6953695772617067526a6b587071586b6c6d586d4a79507242624a416f5545724a4b7138304a36653246674271776776776451414141413d3d";

        // Act
        var result = citySerializer.deserialize(Hex.decodeHex(serializedCityDTO));

        // Assert
        assertEquals(cityDTO, result);
    }
}
