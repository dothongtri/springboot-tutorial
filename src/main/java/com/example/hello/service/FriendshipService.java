package com.example.hello.service;

import java.util.List;

import com.example.hello.dto.FriendResponse;

public interface FriendshipService {
    List<FriendResponse> getFriends(Long userId);
    void addFriend(Long userId, Long friendId);
}
