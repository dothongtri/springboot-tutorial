package com.example.hello.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.example.hello.dto.ChatMessage;

@Controller
public class ChatController {

    @Autowired
    private KafkaTemplate<String, ChatMessage> kafkaTemplate;

    @MessageMapping("/chat")
    public void sendMessage(ChatMessage message, Principal principal) {
        if (principal == null) {
            System.out.println("⚠️ Không tìm thấy principal! Chưa đăng nhập?");
            return;
        }
        String sender = principal.getName();
        message.setSender(sender);
        System.out.println("📤 " + sender + " gửi tin nhắn đến " + message.getReceiver());
        kafkaTemplate.send("chat-messages", message.getReceiver(), message);
    }





}