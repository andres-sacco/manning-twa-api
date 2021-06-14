package com.twa.flights.api.pricing.specification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.google.common.collect.Lists;
import com.twa.flights.api.pricing.model.CommercialPolicy;
import com.twa.flights.api.pricing.model.CommercialPolicyCarrier;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;
import com.twa.flights.common.dto.itinerary.SegmentDTO;

public class CommercialPolicyCarrierSpecification implements Specification<CommercialPolicy> {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    private static final long serialVersionUID = 7724219200247668775L;

    private static final String ACTIVE = "active";
    private static final String START_DATE = "startDate";
    private static final String EXPIRE_DATE = "expireDate";
    private static final String VALIDATING_CARRIERS = "validatingCarriers";
    private static final String CARRIER = "carrier";
    private static final String PRIORITY = "priority";

    private final ItineraryDTO itinerary;

    public CommercialPolicyCarrierSpecification(ItineraryDTO itinerary) {
        this.itinerary = itinerary;
    }

    private Predicate[] convertPredicatesToArray(List<Predicate> restrictions) {
        return restrictions.toArray(new Predicate[restrictions.size()]);
    }

    private LocalDateTime convertDeparture(SegmentDTO segment) {
        String dateTime = segment.firstLeg().getDepartureDate().concat(" ")
                .concat(segment.firstLeg().getDepartureTime());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDateTime.parse(dateTime, formatter);
    }

    private LocalDateTime convertArrival(SegmentDTO segment) {
        String dateTime = segment.firstLeg().getArrivalDate().concat(" ").concat(segment.firstLeg().getArrivalTime());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDateTime.parse(dateTime, formatter);
    }

    @Override
    public Predicate toPredicate(Root<CommercialPolicy> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> restrictions = Lists.newArrayList();

        restrictions.add(builder.isTrue(root.get(CommercialPolicyCarrierSpecification.ACTIVE)));

        restrictions.add(builder.lessThanOrEqualTo(root.get(CommercialPolicyCarrierSpecification.START_DATE),
                convertDeparture(itinerary.firstSegment())));
        restrictions.add(builder.greaterThanOrEqualTo(root.get(CommercialPolicyCarrierSpecification.EXPIRE_DATE),
                convertArrival(itinerary.lastSegment())));

        Join<CommercialPolicy, CommercialPolicyCarrier> carriers = root
                .join(CommercialPolicyCarrierSpecification.VALIDATING_CARRIERS);
        restrictions
                .add(builder.equal(carriers.get(CommercialPolicyCarrierSpecification.CARRIER), itinerary.getCarrier()));

        query.orderBy(builder.desc(root.get(CommercialPolicyCarrierSpecification.PRIORITY)));

        return builder.and(convertPredicatesToArray(restrictions));
    }
}
