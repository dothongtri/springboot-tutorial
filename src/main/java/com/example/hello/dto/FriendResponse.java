package com.example.hello.dto;

import java.time.Instant;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendResponse {
    private Long id;
    private String username;
    private String email;
    private String avt;
    private String lastMessage;
    private String sender;
    private LocalDateTime lastMessageTime;
}
