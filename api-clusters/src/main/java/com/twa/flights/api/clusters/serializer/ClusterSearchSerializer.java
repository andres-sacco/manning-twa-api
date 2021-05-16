package com.twa.flights.api.clusters.serializer;

import com.twa.flights.api.clusters.dto.ClusterSearchDTO;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

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
