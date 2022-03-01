package com.twa.flights.api.provider.beta.serializer.compressor;

import com.twa.flights.api.provider.beta.dto.CityDTO;
import com.twa.flights.api.provider.beta.serializer.CitySerializer;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CitySerializerTest {

   CitySerializer citySerializer = new CitySerializer();

   @Test
   void givenCityDTOWhenSerializationIsAppliedThenObjectShouldBeSerialized() {
      // Arrange
      var cityDTO = new CityDTO();
      cityDTO.setCode("AR-B");
      cityDTO.setName("Buenos Aires");
      cityDTO.setTimeZone("GMT-3");

      var serializedCityDTO = "483473494141414141414141414b7457796b764d54565779556e49715463334c4c315a777a43784b4c5662535555724f5477474a4f6762704f6746354a5a6d35716646562b586b67495866664546316a70566f417338493452546b414141413d";

      // Act
      var result = citySerializer.serialize(cityDTO);

      // Assert
      assertEquals(serializedCityDTO, Hex.encodeHexString(result));
   }

   @Test
   void givenSerializedCityDTOWhenDeserializationIsAppliedThenObjectShouldBeDeserialized() throws Exception {
      // Arrange
      var cityDTO = new CityDTO();
      cityDTO.setCode("AR-B");
      cityDTO.setName("Buenos Aires");
      cityDTO.setTimeZone("GMT-3");

      var serializedCityDTO = "483473494141414141414141414b7457796b764d54565779556e49715463334c4c315a777a43784b4c5662535555724f5477474a4f6762704f6746354a5a6d35716646562b586b67495866664546316a70566f417338493452546b414141413d";

      // Act
      var result = citySerializer.deserialize(Hex.decodeHex(serializedCityDTO));

      // Assert
      assertEquals(cityDTO, result);
   }
}
