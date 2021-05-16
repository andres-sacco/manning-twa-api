package com.twa.flights.api.provider.beta.serializer;

import com.twa.flights.api.provider.beta.dto.CityDTO;
import org.apache.commons.lang3.SerializationException;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class CitySerializer implements RedisSerializer<CityDTO> {
    @Override
    public byte[] serialize(CityDTO cityDTO) throws SerializationException {
        return JsonSerializer.serialize(cityDTO);
    }

    @Override
    public CityDTO deserialize(byte[] bytes) throws SerializationException {
        return JsonSerializer.deserialize(bytes, CityDTO.class);
    }
}
