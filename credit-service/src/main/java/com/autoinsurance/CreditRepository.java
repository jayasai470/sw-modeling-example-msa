package com.autoinsurance;

import com.autoinsurance.entities.Credit;
import org.metaworks.multitenancy.persistence.MultitenantRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by uengine on 2017. 7. 3..
 */
@RepositoryRestResource(collectionResourceRel = "credits", path="credit")
public interface CreditRepository extends MultitenantRepository<Credit, String> {
}
