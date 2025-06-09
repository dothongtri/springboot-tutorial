package com.example.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.example.hello.dto.ChatMessage;



@Controller
public class ChatController {

    @Autowired
    private KafkaTemplate<String, ChatMessage> kafkaTemplate;

    private static final String TOPIC = System.getProperty("KAFKA-TOPIC");

    @MessageMapping("/chat")
    public void sendMessage(ChatMessage message) {
        kafkaTemplate.send(TOPIC, message.getReceiver(), message);
    }
}