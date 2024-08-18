package com.example.customerservice.service;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducerService {


    private  KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topic, Object obj) {
        kafkaTemplate.send(topic, obj);
    }
}