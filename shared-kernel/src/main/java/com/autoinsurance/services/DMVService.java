package com.autoinsurance.services;

import com.autoinsurance.entities.License;
import com.autoinsurance.entities.Vehicle;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by uengine on 2017. 12. 3..
 */

@FeignClient(serviceId = "dmv-service")
public interface DMVService {

    @RequestMapping(path="/owners/{ssn}/vehicles", method= RequestMethod.GET)
    public List<Vehicle> getVehicles(@PathVariable("ssn") String ssn);

    @RequestMapping(path="/owners/{ssn}/license", method= RequestMethod.GET)
    public License getLicense(@PathVariable("ssn") String ssn);


}
