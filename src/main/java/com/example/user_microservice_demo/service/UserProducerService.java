package com.example.user_microservice_demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.example.user_microservice_demo.util.constants.GlobalConstants.TOPIC_USER_DELETION;

@Service
@RequiredArgsConstructor
public class UserProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendDeleteUserMessage(Long userId) {
        kafkaTemplate.send(TOPIC_USER_DELETION, String.valueOf(userId));
    }
}
