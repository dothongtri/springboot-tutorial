package com.example.hello.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.hello.dto.ChatMessage;



@Service
public class KafkaConsumerService {

    private final SimpMessagingTemplate messagingTemplate;

    private final static String TOPIC = "chat-messages";

    public KafkaConsumerService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = TOPIC)
    public void listen(ChatMessage message) {
        // Giả lập lưu DB: System.out.println("Saved: " + message.getContent());
        messagingTemplate.convertAndSendToUser(
            message.getReceiver(), "/queue/messages", message
        );
    }
}
