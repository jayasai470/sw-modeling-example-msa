package com.autoinsurance.services;

import com.autoinsurance.entities.Credit;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by uengine on 2017. 12. 3..
 */

@FeignClient(serviceId = "credit-service")
public interface CreditService {

    @RequestMapping(path="/credits/{ssn}", method= RequestMethod.GET)
    public Credit getCredit(@PathVariable("ssn") String ssn);

}
