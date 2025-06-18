package com.example.hello.dto;

import lombok.Data;

@Data
public class FriendResponse {
    private Long id;
    private String username;
    private String email;
}
