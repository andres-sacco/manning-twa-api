package com.twa.flights.api.clusters.repository.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import com.twa.flights.api.clusters.dto.ClusterSearchDTO;
import com.twa.flights.api.clusters.dto.response.PaginationDTO;
import com.twa.flights.api.clusters.helper.FlightIdGeneratorHelper;
import com.twa.flights.api.clusters.repository.ClustersRepository;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;
import com.twa.flights.common.dto.request.AvailabilityRequestDTO;

@Repository
public class ClustersRepositoryImpl implements ClustersRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClustersRepositoryImpl.class);
    private static final long TOKEN_TTL = 5 * 60 * 1000L; // 5 Minutes

    private final ValueOperations<String, ClusterSearchDTO> valueOperations;
    private final RedisTemplate<String, ClusterSearchDTO> redisTemplate;
    private final ExecutorService executorService;
    private final FlightIdGeneratorHelper flightIdGeneratorHelper;

    @Autowired
    public ClustersRepositoryImpl(RedisTemplate<String, ClusterSearchDTO> redisTemplate,
            FlightIdGeneratorHelper flightIdGeneratorHelper) {
        this.flightIdGeneratorHelper = flightIdGeneratorHelper;
        this.executorService = Executors.newFixedThreadPool(10);
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public ClusterSearchDTO insert(AvailabilityRequestDTO availabilityRequest, List<ItineraryDTO> itineraries) {
        LOGGER.debug("Insert all the information in the database");

        PaginationDTO pagination = new PaginationDTO(0, availabilityRequest.getAmount(), itineraries.size());
        ClusterSearchDTO dataToInsert = new ClusterSearchDTO(flightIdGeneratorHelper.generate(availabilityRequest),
                itineraries, pagination);

        executorService.submit(() -> {
            redisTemplate.execute((RedisCallback<Object>) connection -> {
                @SuppressWarnings("unchecked")
                RedisSerializer<String> keySerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
                @SuppressWarnings("unchecked")
                RedisSerializer<ClusterSearchDTO> valueSerializer = (RedisSerializer<ClusterSearchDTO>) redisTemplate
                        .getValueSerializer();

                connection.pSetEx(keySerializer.serialize(dataToInsert.getId()), TOKEN_TTL,
                        valueSerializer.serialize(dataToInsert));
                return null;
            });
        });
        return new ClusterSearchDTO(dataToInsert.getId(), itineraries, pagination);
    }

    @Override
    public ClusterSearchDTO get(String id) {

        ClusterSearchDTO clusterSearch = valueOperations.get(id);
        if (clusterSearch == null) {
            LOGGER.error("clusterSearch information with id: {}' not found in repository", id);
        }

        return clusterSearch;
    }

}
