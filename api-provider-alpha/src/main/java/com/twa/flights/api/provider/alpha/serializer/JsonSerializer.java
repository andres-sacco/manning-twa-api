package com.twa.flights.api.provider.alpha.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer<T> {
	
    public static byte[] serialize(Object object) {
    	try {
			return new ObjectMapper().writeValueAsBytes(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }	
    
    public static <T> T deserialize(byte[] raw, Class<T> reference) {
    	try {
			return new ObjectMapper().readValue(raw, reference);
		} catch (JsonParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }

}
