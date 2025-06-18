package com.example.hello.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.hello.entity.Message;
import com.example.hello.entity.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE " +
            "(m.sender = :user1 AND m.receiver = :user2) OR " +
            "(m.sender = :user2 AND m.receiver = :user1) " +
            "ORDER BY m.sentAt ASC")
    List<Message> findMessagesBetweenUsers(User user1, User user2);

    // List<Message> findByChatRoomId(Long chatRoomId); // Uncomment if you want to
    // use chat room ID

}
