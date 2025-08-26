package com.aish.service;

import com.aish.model.Citizen;
import com.aish.producer.CitizenProducer;
import com.aish.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenService {

    @Autowired
    CitizenRepository repository;
    @Autowired
    CitizenProducer producer;


    public Citizen getCitizenById(Integer id) {
        return repository.findById(id).
                orElseThrow(() ->
                        new RuntimeException("Citizen not found with id: " + id));
    }

//    public Citizen createCitizen(Citizen citizen) {
//        return repository.save(citizen);
//    }

    public List<Citizen> getCitizens(Integer centerId) {
        return repository.findByVaccinationCenterId(centerId);
    }

    public Citizen createCitizen(Citizen citizen) {
        Citizen saved = repository.save(citizen);

        // 🔥 Publish Kafka event
        producer.sendCitizenEvent("Citizen Registered: "
                + saved.getName()
                + " (ID: " + saved.getId() + ")");

        return saved;
    }
}
