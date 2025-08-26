package com.aish.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CitizenProducer {

    private static final String TOPIC = "citizen-events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendCitizenEvent(String message) {
        System.out.println("Producing Kafka message: " + message);
        kafkaTemplate.send(TOPIC, message);
    }
}

