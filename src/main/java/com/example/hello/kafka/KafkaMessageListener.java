package com.example.hello.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.example.hello.dto.ChatMessage;
import com.example.hello.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KafkaMessageListener {

    @Autowired
    MessageService messageService; // Inject MessageService if needed

    private final SimpMessagingTemplate messagingTemplate;

    public KafkaMessageListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "chat-messages")
    public void listen(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ChatMessage msg = mapper.readValue(json, ChatMessage.class);
            // System.out.println("==================Received message: " + msg.getReceiver());
            // gửi về user cụ thể theo username (Principal.getName())
            messagingTemplate.convertAndSendToUser(msg.getReceiver(), "/queue/messages", msg);
            messageService.saveMessage(msg); // Save the message using MessageService

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
