package com.twa.flights.api.clusters.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.twa.flights.api.clusters.dto.ClusterSearchDTO;

@Service
public class ClusterSearchSerializer implements RedisSerializer<ClusterSearchDTO> {

    @Override
    public byte[] serialize(ClusterSearchDTO clusterSearch) {
        return JsonSerializer.serialize(clusterSearch);
    }

    @Override
    public ClusterSearchDTO deserialize(byte[] bytes) {
        return JsonSerializer.deserialize(bytes, ClusterSearchDTO.class);
    }
}
