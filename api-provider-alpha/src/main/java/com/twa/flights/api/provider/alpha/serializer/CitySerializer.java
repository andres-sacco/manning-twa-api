package com.twa.flights.api.provider.alpha.serializer;

import com.twa.flights.api.provider.alpha.dto.CityDTO;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
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
