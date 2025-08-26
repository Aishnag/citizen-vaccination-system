package com.aish.client;

import com.aish.model.Citizen;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "citizen-service",
        path = "/api/v1"
)
public interface CitizenFeignClient {

    @GetMapping("/citizens/{centerId}")
    List<Citizen> getCitizensByCenterId(@PathVariable("centerId") Integer centerId);
}
