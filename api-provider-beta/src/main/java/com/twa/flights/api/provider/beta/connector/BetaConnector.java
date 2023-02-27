package com.twa.flights.api.provider.beta.connector;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.google.common.collect.Lists;
import com.twa.flights.common.dto.enums.FlightType;
import com.twa.flights.common.dto.enums.PassengerType;
import com.twa.flights.common.dto.enums.Provider;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;
import com.twa.flights.common.dto.itinerary.LegDTO;
import com.twa.flights.common.dto.itinerary.PaxPriceDTO;
import com.twa.flights.common.dto.itinerary.PriceInfoDTO;
import com.twa.flights.common.dto.itinerary.SegmentDTO;
import com.twa.flights.common.dto.request.AvailabilityRequestDTO;

@Component
public class BetaConnector {

    private List<String> carriers = List.of("AR", "AA", "DL", "IB", "FY", "LA", "AF", "KL", "JJ");

    public List<ItineraryDTO> availability(AvailabilityRequestDTO availabilityRequest) {
        List<ItineraryDTO> itineraries = Lists.newArrayList();

        for (int i = 0; i < availabilityRequest.getAmount(); i++) {
            itineraries.add(getItinerary(availabilityRequest));
        }

        return itineraries;
    }

    private ItineraryDTO getItinerary(AvailabilityRequestDTO availabilityRequest) {

        Faker faker = new Faker();

        ItineraryDTO itinerary = new ItineraryDTO();
        String[] departure = availabilityRequest.getTo().split(",");

        itinerary.setCarrier(carriers.get(faker.number().numberBetween(0, 9)));
        itinerary.setProvider(Provider.BETA);

        if (departure.length > 1) {
            itinerary.setFlightType(FlightType.ROUND_TRIP);
        } else {
            itinerary.setFlightType(FlightType.ONE_WAY);
        }

        itinerary.setId(UUID.randomUUID().toString());
        itinerary.setPriceInfo(createPriceInfo(faker, availabilityRequest));
        itinerary.setSegments(createSegments(faker, availabilityRequest));

        return itinerary;
    }

    private List<SegmentDTO> createSegments(Faker faker, AvailabilityRequestDTO availabilityRequest) {
        List<SegmentDTO> segments = Lists.newArrayList();

        String[] departureDate = availabilityRequest.getDeparture().split(",");
        String[] origin = availabilityRequest.getFrom().split(",");
        String[] destination = availabilityRequest.getTo().split(",");

        createSegmentOrigin(faker, segments, departureDate, origin, destination);

        if (departureDate.length > 1) {
            createSegmentReturn(faker, segments, departureDate, origin, destination);
        }

        return segments;
    }

    private void createSegmentReturn(Faker faker, List<SegmentDTO> segments, String[] departureDate, String[] origin,
            String[] destination) {
        SegmentDTO segmentReturn = new SegmentDTO();
        List<LegDTO> legs = Lists.newArrayList();
        LegDTO leg = new LegDTO(origin[1], destination[1], departureDate[1], "12:00", departureDate[1], "18:00",
                faker.number().randomDigit(), null);
        legs.add(leg);

        segmentReturn.setLegs(legs);
        segments.add(segmentReturn);
    }

    private void createSegmentOrigin(Faker faker, List<SegmentDTO> segments, String[] departureDate, String[] origin,
            String[] destination) {
        SegmentDTO segmentOrigin = new SegmentDTO();
        List<LegDTO> legs = Lists.newArrayList();
        LegDTO leg = new LegDTO(origin[0], destination[0], departureDate[0], "06:00", departureDate[0], "12:00",
                faker.number().randomDigit(), null);
        legs.add(leg);

        segmentOrigin.setLegs(legs);
        segments.add(segmentOrigin);
    }

    private PriceInfoDTO createPriceInfo(Faker faker, AvailabilityRequestDTO availabilityRequest) {
        PriceInfoDTO priceInfo = new PriceInfoDTO();

        priceInfo.setAdults(createPaxPrice(faker, availabilityRequest.getAdults(), PassengerType.ADULT));

        if (availabilityRequest.getChildren() != null && availabilityRequest.getChildren().intValue() != 0) {
            priceInfo.setChildren(createPaxPrice(faker, availabilityRequest.getChildren(), PassengerType.CHILD));
        }

        if (availabilityRequest.getInfants() != null && availabilityRequest.getInfants().intValue() != 0) {
            priceInfo.setInfants(createPaxPrice(faker, availabilityRequest.getInfants(), PassengerType.INFANT));
        }

        return priceInfo;
    }

    private PaxPriceDTO createPaxPrice(Faker faker, int quantity, PassengerType type) {
        PaxPriceDTO paxPrice = new PaxPriceDTO();

        BigDecimal base = BigDecimal.valueOf(faker.number().randomDouble(2, 1, 1000));
        BigDecimal tax = BigDecimal.valueOf(faker.number().randomDouble(2, 0, 250));
        BigDecimal subtotal = (base.add(tax)).multiply(BigDecimal.valueOf(quantity));

        paxPrice.setBase(base);
        paxPrice.setTax(tax);
        paxPrice.setQuantity(quantity);
        paxPrice.setSubtotal(subtotal);
        paxPrice.setTotal(subtotal);
        paxPrice.setType(type);

        return paxPrice;
    }
}
