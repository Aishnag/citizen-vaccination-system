package com.aish.service;

import com.aish.client.CitizenFeignClient;
import com.aish.entity.VaccinationCenter;
import com.aish.model.Citizen;
import com.aish.repository.VaccinationCenterRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class VaccinationCenterService {
    private final VaccinationCenterRepo repository;
    private final CitizenFeignClient citizenFeignClient;

    public VaccinationCenterService(VaccinationCenterRepo repository, CitizenFeignClient citizenFeignClient) {
        this.repository = repository;
        this.citizenFeignClient = citizenFeignClient;
    }

    public VaccinationCenter getVaccinationCenterById(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("VaccinationCenter not found with id: " + id));
    }

    public VaccinationCenter addVaccinationCenter(VaccinationCenter center) {
        return repository.save(center);
    }

    @CircuitBreaker(name = "citizen-service", fallbackMethod = "citizenFallBack")
    @Retry(name = "citizen-service")
    public List<Citizen> getCitizensForCenter(Integer id) {
        return citizenFeignClient.getCitizensByCenterId(id);
    }

    public List<Citizen> citizenFallBack(Integer id,Throwable ex) {
        return Collections.emptyList();

    }
}
