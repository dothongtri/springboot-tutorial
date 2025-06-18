package com.example.hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello.entity.Message;
import com.example.hello.service.MessageService;

@RestController
public class MessageController {
    @Autowired
    MessageService messageService;
    
    @GetMapping("/api/messages")
    public ResponseEntity<List<Message>> getMessages(@RequestParam String senderId, @RequestParam String receiverId) {
        List<Message> listMessage =  messageService.getMessagesBySenderAndReceiver(senderId, receiverId);
        // This is a placeholder. You can implement your logic to fetch messages here.
        return ResponseEntity.ok(listMessage);
    }
}
