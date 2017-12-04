package com.autoinsurance.services;

import com.autoinsurance.entities.Brand;
import com.autoinsurance.entities.Credit;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by uengine on 2017. 12. 3..
 */

@FeignClient(serviceId = "gmv-service")
public interface GMVService {

    @RequestMapping(path="/vehicle-detail/{modelId}/brand", method= RequestMethod.GET)
    public Brand getBrand(@PathVariable("modelId") String modelId);

}
