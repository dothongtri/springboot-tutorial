package com.example.hello.service;

import java.util.List;

import com.example.hello.dto.ChatMessage;
import com.example.hello.dto.FriendResponse;
import com.example.hello.dto.UnreadMessageCount;
import com.example.hello.entity.Message;

public interface MessageService {
    Message saveMessage(ChatMessage chatMessage); // Gửi tin nhắn đến người dùng cụ thể
    // List<Message> getMessagesByChatRoomId(Long chatRoomId); // Lấy danh sách tin
    // nhắn theo chat room ID

    List<Message> getMessagesBySenderAndReceiver(String sender, String receiver); // Lấy danh sách tin nhắn giữa người
                                                                                  // gửi và người nhận

    List<FriendResponse> findConversationsWithLastMessage(Long userId);

    List<UnreadMessageCount> getUnreadMessageCounts(Long userId);

    // Đánh dấu tin nhắn từ một người đã đọc
    void markMessagesAsRead(Long receiverId, Long senderId);

}
