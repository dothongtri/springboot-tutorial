package com.example.hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello.dto.UnreadMessageCount;
import com.example.hello.entity.Message;
import com.example.hello.service.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    MessageService messageService;
    
    @GetMapping("/list")
    public ResponseEntity<List<Message>> getMessages(@RequestParam String senderId, @RequestParam String receiverId) {
        List<Message> listMessage =  messageService.getMessagesBySenderAndReceiver(senderId, receiverId);
        // This is a placeholder. You can implement your logic to fetch messages here.
        return ResponseEntity.ok(listMessage);
    }

    // Lấy số tin nhắn chưa đọc theo từng người
    @GetMapping("/unread-counts")
    public ResponseEntity<List<UnreadMessageCount>> getUnreadCounts(
            @RequestParam Long userId) {
        List<UnreadMessageCount> counts = messageService.getUnreadMessageCounts(userId);
        return ResponseEntity.ok(counts);
    }

    // Đánh dấu tin nhắn đã đọc
    @PostMapping("/mark-read")
    public ResponseEntity<Void> markAsRead(
            @RequestParam Long receiverId,
            @RequestParam Long senderId) {
        messageService.markMessagesAsRead(receiverId, senderId);
        return ResponseEntity.ok().build();
    }
}
