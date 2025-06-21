package com.example.hello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnreadMessageCount {
    private Long senderId;
    private String senderUsername;
    private Long unreadCount;
}
