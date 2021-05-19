package com.twa.flights.api.provider.beta.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.twa.flights.api.provider.beta.dto.CityDTO;

public class CitySerializer implements RedisSerializer<CityDTO> {

	@Override
	public byte[] serialize(CityDTO t) throws SerializationException {
		return JsonSerializer.serialize(t);
	}

	@Override
	public CityDTO deserialize(byte[] bytes) throws SerializationException {
		return JsonSerializer.deserialize(bytes, CityDTO.class);
	}
	
}
