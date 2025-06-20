package com.example.hello.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum MessageType {
        TEXT,
        IMAGE,
        VIDEO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @Column(name = "content", nullable = false)
    private String content;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private MessageType type = MessageType.TEXT;

    @ManyToOne
    @JoinColumn(name = "reply_to_user_id")
    private User replyToUser;

    @Column(name = "sent_at")
    private LocalDateTime sentAt = LocalDateTime.now();

}
