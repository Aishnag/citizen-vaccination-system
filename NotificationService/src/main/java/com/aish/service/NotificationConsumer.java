package com.aish.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {


    @KafkaListener(topics = "citizen-events", groupId = "notification-group")
    public void consumeCitizenEvent(String message) {
        System.out.println("📩 NotificationService received: " + message);
        sendNotification(message);
    }

    private void sendNotification(String message) {
        System.out.println("✅ Sending Email/SMS: " + message);
    }
}
