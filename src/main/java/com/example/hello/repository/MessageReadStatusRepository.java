package com.example.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hello.entity.MessageReadStatus;

@Repository
public interface MessageReadStatusRepository extends JpaRepository<MessageReadStatus, Long> {

    // Kiểm tra message đã được đọc chưa
    boolean existsByMessageIdAndUserId(Long messageId, Long userId);
    
    // Đánh dấu message đã đọc
    @Modifying
    @Query("INSERT INTO MessageReadStatus (message, user, readAt) " +
           "SELECT m, u, CURRENT_TIMESTAMP " +
           "FROM Message m, User u " +
           "WHERE m.sender.id = :senderId AND u.id = :receiverId " +
           "AND NOT EXISTS (SELECT 1 FROM MessageReadStatus mrs WHERE mrs.message = m AND mrs.user = u)")
    void markMessagesAsRead(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
}
