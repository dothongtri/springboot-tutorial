package com.example.hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello.dto.FriendResponse;
import com.example.hello.service.FriendshipService;
import com.example.hello.service.MessageService;

@RestController
@RequestMapping("/api/friend")
public class FriendController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{userId}/list")
    public ResponseEntity<List<FriendResponse>> getFriends(@PathVariable Long userId) {
        List<FriendResponse> friends = messageService.findConversationsWithLastMessage(userId);
        return ResponseEntity.ok(friends);
    }

}
