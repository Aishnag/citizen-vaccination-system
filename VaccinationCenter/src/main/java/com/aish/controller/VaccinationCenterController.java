package com.aish.controller;

import com.aish.entity.VaccinationCenter;
import com.aish.model.Citizen;
import com.aish.model.RequiredResponse;
import com.aish.service.VaccinationCenterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v11/centers")
public class VaccinationCenterController {

    private final VaccinationCenterService service;

    public VaccinationCenterController(VaccinationCenterService service) {
        this.service = service;
    }

    @GetMapping("/vaccination/{id}")
    public ResponseEntity<VaccinationCenter> getVaccinationCenterById(@PathVariable Integer id) {
        VaccinationCenter center = service.getVaccinationCenterById(id);
        return new ResponseEntity<>(center, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VaccinationCenter> addVaccinationCenter(@RequestBody VaccinationCenter center) {
        VaccinationCenter saved = service.addVaccinationCenter(center);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/citizens")
    public ResponseEntity<RequiredResponse> getAllDataBasedOnCenterId(@PathVariable Integer id) {
        RequiredResponse response = new RequiredResponse();

        VaccinationCenter center = service.getVaccinationCenterById(id);
        response.setCenter(center);

        List<Citizen> citizens = service.getCitizensForCenter(id);
        response.setCitizens(citizens);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
