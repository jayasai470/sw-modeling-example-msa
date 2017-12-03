package gov.gmv;

import org.metaworks.multitenancy.persistence.MultitenantRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by uengine on 2017. 7. 3..
 */
@RepositoryRestResource(collectionResourceRel = "vehicles", path="vehicle-detail")
public interface VehicleRepository extends MultitenantRepository<Vehicle, String>{
}
