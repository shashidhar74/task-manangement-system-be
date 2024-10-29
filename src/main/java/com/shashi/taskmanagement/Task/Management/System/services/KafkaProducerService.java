package com.shashi.taskmanagement.Task.Management.System.services;

import com.shashi.taskmanagement.Task.Management.System.dto.EmailMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, EmailMessage> kafkaTemplate;
    private static final String TOPIC = "manager-added";

    public void sendEmail(EmailMessage emailMessage) {
        kafkaTemplate.send(TOPIC, emailMessage);
    }
}
