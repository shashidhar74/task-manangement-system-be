package com.shashi.taskmanagement.Task.Management.System.services;

import com.shashi.taskmanagement.Task.Management.System.dto.EmailMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class KafkaConsumerService {

    private final EmailService emailService;

    @KafkaListener(topics = "manager-added", groupId = "task-notifications")
    public void listen(EmailMessage emailMessage) {
        emailService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getBody());
        System.out.println("Email sent to: " + emailMessage.getTo());
    }

}
