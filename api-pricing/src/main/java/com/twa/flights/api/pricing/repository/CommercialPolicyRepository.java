package com.twa.flights.api.pricing.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.twa.flights.api.pricing.model.CommercialPolicy;

@Repository
public interface CommercialPolicyRepository
        extends PagingAndSortingRepository<CommercialPolicy, Long>, JpaSpecificationExecutor<CommercialPolicy> {

}
