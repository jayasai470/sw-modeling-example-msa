package com.autoinsurance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface InsurancePolicyRepository extends PagingAndSortingRepository<InsurancePolicy, Long> {
}
