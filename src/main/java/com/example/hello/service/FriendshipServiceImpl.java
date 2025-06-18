package com.example.hello.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello.dto.FriendResponse;
import com.example.hello.entity.User;
import com.example.hello.repository.FriendshipRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Override
    public List<FriendResponse> getFriends(Long userId) {
        List<User> friends = friendshipRepository.findAcceptedFriendsByUserId(userId);
        return friends.stream()
                .map(user -> {
                    FriendResponse friendDTO = new FriendResponse();
                    friendDTO.setId(user.getId());
                    friendDTO.setUsername(user.getUsername());
                    friendDTO.setEmail(user.getEmail());
                    return friendDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addFriend(Long userId, Long friendId) {
        // Implementation for adding a friend
        // This would typically involve creating a new Friendship entity and saving it
    }

}
