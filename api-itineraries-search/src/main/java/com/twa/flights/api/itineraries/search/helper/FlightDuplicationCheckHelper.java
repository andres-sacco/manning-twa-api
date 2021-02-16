package com.twa.flights.api.itineraries.search.helper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;

@Component
public class FlightDuplicationCheckHelper {

    private ItineraryKeyGeneratorHelper itineraryKeyGeneratorHelper;

    @Autowired
    public FlightDuplicationCheckHelper(ItineraryKeyGeneratorHelper itineraryKeyGeneratorHelper) {
        this.itineraryKeyGeneratorHelper = itineraryKeyGeneratorHelper;
    }

    public List<ItineraryDTO> removeDuplicateFlights(List<ItineraryDTO> flights) {

        HashMap<String, ItineraryDTO> itinerariesMap = Maps.newHashMap();

        flights.stream().forEach(itinerary -> {
            String itineraryKey = itineraryKeyGeneratorHelper.generateKeyForItinerary(itinerary);

            if (itinerariesMap.putIfAbsent(itineraryKey, itinerary) != null) {

                ItineraryDTO oldItinerary = itinerariesMap.get(itineraryKey);

                if (isBetterPrice(oldItinerary, itinerary, 0d)) {
                    itinerariesMap.put(itineraryKey, itinerary);
                }
            }

        });

        return Lists.newArrayList(itinerariesMap.values());
    }

    private boolean isBetterPrice(ItineraryDTO iter1, ItineraryDTO iter2, Double tolerance) {
        BigDecimal subtract = iter1.getPriceInfo().getTotalAmount().subtract(iter2.getPriceInfo().getTotalAmount());

        return subtract.compareTo(BigDecimal.ZERO) > 0 ? tolerance.compareTo(subtract.doubleValue()) <= 0 : false;
    }

}
