package com.aish.controller;

import com.aish.model.Citizen;
import com.aish.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CitizenController {

    @Autowired
    CitizenService service;

    @GetMapping("/citizen/{id}")
    public ResponseEntity<Citizen> getCitizenById(@PathVariable Integer id) {
        return new ResponseEntity<Citizen>(service.getCitizenById(id), HttpStatus.OK);
    }

    @GetMapping("/citizens/{centerId}")
    public ResponseEntity<List<Citizen>> getCitizens(@PathVariable Integer centerId) {
        List<Citizen> citizens = service.getCitizens(centerId);
        return new ResponseEntity<>(citizens, HttpStatus.OK);
    }

    @PostMapping("/citizen")
    public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen citizen) {
        return new ResponseEntity<>(service.createCitizen(citizen), HttpStatus.CREATED);
    }

}
