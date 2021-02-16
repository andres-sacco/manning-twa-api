package com.twa.flights.api.pricing.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.twa.flights.api.pricing.dto.UpdatedPaxPriceDTO;
import com.twa.flights.api.pricing.dto.UpdatedPriceInfoDTO;
import com.twa.flights.api.pricing.model.CommercialPolicy;
import com.twa.flights.api.pricing.model.CommercialPolicyCarrier;
import com.twa.flights.api.pricing.repository.CommercialPolicyRepository;
import com.twa.flights.api.pricing.specification.CommercialPolicyCarrierSpecification;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;
import com.twa.flights.common.dto.itinerary.MarkupDTO;
import com.twa.flights.common.dto.itinerary.PaxPriceDTO;

@Service
public class PricingService {

    CommercialPolicyRepository commercialPolicyRepository;

    @Autowired
    public PricingService(CommercialPolicyRepository commercialPolicyRepository) {
        this.commercialPolicyRepository = commercialPolicyRepository;
    }

    public List<UpdatedPriceInfoDTO> priceItineraries(List<ItineraryDTO> itineraries) {
        List<UpdatedPriceInfoDTO> response = Lists.newArrayList();

        for (ItineraryDTO itinerary : itineraries) {

            CommercialPolicyCarrierSpecification specification = new CommercialPolicyCarrierSpecification(itinerary);

            List<CommercialPolicy> policies = commercialPolicyRepository.findAll(specification);

            Optional<CommercialPolicy> optionalPolicy = policies.stream().findFirst();

            if (optionalPolicy.isPresent()) {
                CommercialPolicy policy = optionalPolicy.get();
                Optional<CommercialPolicyCarrier> optionalPolicyCarrier = policy.getValidatingCarriers().stream()
                        .filter(carrier -> carrier.getCarrier().equals(itinerary.getCarrier())).findAny();
                response.add(calculateMarkup(itinerary, optionalPolicyCarrier));
            }
        }

        return response;
    }

    private UpdatedPriceInfoDTO calculateMarkup(ItineraryDTO itinerary,
            Optional<CommercialPolicyCarrier> optionalPolicyCarrier) {

        UpdatedPriceInfoDTO updatedPriceInfo = new UpdatedPriceInfoDTO();
        updatedPriceInfo.setItineraryId(itinerary.getId());

        updatedPriceInfo.setAdults(calculateMarkupByType(optionalPolicyCarrier, itinerary.getPriceInfo().getAdults()));

        if (itinerary.getPriceInfo().getChildren() != null) {
            updatedPriceInfo
                    .setChildren(calculateMarkupByType(optionalPolicyCarrier, itinerary.getPriceInfo().getChildren()));
        }

        if (itinerary.getPriceInfo().getInfants() != null) {
            updatedPriceInfo
                    .setInfants(calculateMarkupByType(optionalPolicyCarrier, itinerary.getPriceInfo().getInfants()));
        }

        return updatedPriceInfo;
    }

    private UpdatedPaxPriceDTO calculateMarkupByType(Optional<CommercialPolicyCarrier> optionalPolicyCarrier,
            PaxPriceDTO paxPrice) {
        UpdatedPaxPriceDTO updatedPaxPrice = new UpdatedPaxPriceDTO();
        if (optionalPolicyCarrier.isPresent()) {
            CommercialPolicyCarrier policyCarrier = optionalPolicyCarrier.get();

            BigDecimal amount = paxPrice.getSubtotal()
                    .multiply(policyCarrier.getPercentage().divide(BigDecimal.valueOf(10)));
            updatedPaxPrice.setTotal(paxPrice.getTotal().add(amount));
            updatedPaxPrice.setMarkup(new MarkupDTO(policyCarrier.getPercentage(), amount));
        }

        return updatedPaxPrice;
    }
}
