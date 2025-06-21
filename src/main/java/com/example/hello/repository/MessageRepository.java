package com.example.hello.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hello.dto.FriendResponse;
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
  @Query("""
                           SELECT new com.example.hello.dto.FriendResponse(
          CASE WHEN m.sender.id = :currentUserId THEN m.receiver.id ELSE m.sender.id END,
          CASE WHEN m.sender.id = :currentUserId THEN m.receiver.username ELSE m.sender.username END,
          CASE WHEN m.sender.id = :currentUserId THEN m.receiver.email ELSE m.sender.email END,
          CASE WHEN m.sender.id = :currentUserId THEN m.receiver.avatar_url ELSE m.sender.avatar_url END,
          m.content,
          m.sender.email,
          m.sentAt
      )
      FROM Message m
      WHERE (m.sender.id = :currentUserId OR m.receiver.id = :currentUserId)
        AND m.sentAt = (
          SELECT MAX(m2.sentAt)
          FROM Message m2
          WHERE (
            (m2.sender.id = :currentUserId AND m2.receiver.id = CASE WHEN m.sender.id = :currentUserId THEN m.receiver.id ELSE m.sender.id END)
            OR (m2.sender.id = CASE WHEN m.sender.id = :currentUserId THEN m.receiver.id ELSE m.sender.id END AND m2.receiver.id = :currentUserId)
          )
        )
      ORDER BY m.sentAt DESC

                          """)
  List<FriendResponse> findConversationsWithLastMessage(@Param("currentUserId") Long userId);

  // Đếm tin nhắn chưa đọc theo từng sender
  @Query("SELECT m.sender.id, m.sender.username, COUNT(m) " +
      "FROM Message m " +
      "WHERE m.receiver.id = :userId " +
      "AND NOT EXISTS (SELECT 1 FROM MessageReadStatus mrs WHERE mrs.message = m AND mrs.user.id = :userId) " +
      "GROUP BY m.sender.id, m.sender.username")
  List<Object[]> countUnreadMessagesBySender(@Param("userId") Long userId);

}
