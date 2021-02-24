package com.twa.flights.api.clusters.repository.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import com.twa.flights.api.clusters.dto.ClusterSearchDTO;
import com.twa.flights.api.clusters.helper.FlightIdGeneratorHelper;
import com.twa.flights.api.clusters.repository.ClustersRepository;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;
import com.twa.flights.common.dto.request.AvailabilityRequestDTO;

@Repository
public class ClustersRepositoryImpl implements ClustersRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClustersRepositoryImpl.class);
    private static final long TOKEN_TTL = 5 * 60L;

    private final RedisTemplate<String, ClusterSearchDTO> redisTemplate;
    private final ExecutorService executorService;
    private final FlightIdGeneratorHelper flightIdGeneratorHelper;

    @Autowired
    public ClustersRepositoryImpl(RedisTemplate<String, ClusterSearchDTO> redisTemplate,
            FlightIdGeneratorHelper flightIdGeneratorHelper) {
        this.flightIdGeneratorHelper = flightIdGeneratorHelper;
        this.executorService = Executors.newFixedThreadPool(10);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ClusterSearchDTO insert(AvailabilityRequestDTO availabilityRequest, List<ItineraryDTO> itineraries) {
        LOGGER.debug("Insert all the information in the database");

        ClusterSearchDTO response = new ClusterSearchDTO(flightIdGeneratorHelper.generate(availabilityRequest),
                itineraries);

        executorService.submit(() -> {
            redisTemplate.execute((RedisCallback<Object>) connection -> {
                @SuppressWarnings("unchecked")
                RedisSerializer<String> keySerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
                @SuppressWarnings("unchecked")
                RedisSerializer<ClusterSearchDTO> valueSerializer = (RedisSerializer<ClusterSearchDTO>) redisTemplate
                        .getValueSerializer();

                connection.pSetEx(keySerializer.serialize(response.getId()), TOKEN_TTL,
                        valueSerializer.serialize(response));
                return null;
            });
        });
        return response;
    }

}
