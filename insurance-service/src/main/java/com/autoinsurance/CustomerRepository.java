package com.autoinsurance;

import org.metaworks.multitenancy.persistence.MultitenantRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.Parameter;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by uengine on 2017. 7. 3..
 */
@RepositoryRestResource
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    List<Customer> findByFirstName(@Param("firstName") String firstName);

}
