package com.example.hello.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello.dto.ChatMessage;
import com.example.hello.dto.FriendResponse;
import com.example.hello.dto.UnreadMessageCount;
import com.example.hello.entity.Message;
import com.example.hello.entity.User;
import com.example.hello.repository.MessageReadStatusRepository;
import com.example.hello.repository.MessageRepository;
import com.example.hello.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

        @Autowired
        private MessageRepository messageRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private MessageReadStatusRepository readStatusRepository;

        // Implement the method to save a message
        @Override
        public Message saveMessage(ChatMessage chatMessage) {
                User sender = userRepository.findByEmail(chatMessage.getSender())
                                .orElseThrow(() -> new RuntimeException("Sender not found"));
                User receiver = userRepository.findByEmail(chatMessage.getReceiver())
                                .orElseThrow(() -> new RuntimeException("Receiver not found"));
                // Logic to save the message
                // This is just a placeholder, actual implementation will depend on your data
                // access layer
                Message message = new Message();
                message.setSender(sender);
                message.setReceiver(receiver);
                message.setContent(chatMessage.getContent());

                // Save the message to the database (not implemented here)

                return messageRepository.save(message); // Return the saved message
        }

        public List<Message> getMessagesBySenderAndReceiver(String sender, String receiver) {
                User senderUser = userRepository.findByEmail(sender)
                                .orElseThrow(() -> new RuntimeException("Sender not found"));
                User receiverUser = userRepository.findByEmail(receiver)
                                .orElseThrow(() -> new RuntimeException("Receiver not found"));

                return messageRepository.findMessagesBetweenUsers(senderUser, receiverUser);
        }

        @Override
        public List<FriendResponse> findConversationsWithLastMessage(Long userId) {
                return messageRepository.findConversationsWithLastMessage(userId);
        }

        // Lấy số tin nhắn chưa đọc theo từng người gửi
        public List<UnreadMessageCount> getUnreadMessageCounts(Long userId) {
                List<Object[]> results = messageRepository.countUnreadMessagesBySender(userId);

                return results.stream()
                                .map(result -> new UnreadMessageCount(
                                                (Long) result[0], // senderId
                                                (String) result[1], // senderUsername
                                                (Long) result[2] // unreadCount
                                ))
                                .collect(Collectors.toList());
        }

        @Override
        public void markMessagesAsRead(Long receiverId, Long senderId) {
                readStatusRepository.markMessagesAsRead(senderId, receiverId);
        }



}
