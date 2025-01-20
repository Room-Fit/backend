package com.roomfit.be.chat.domain;

import com.roomfit.be.global.entity.BaseEntity;
import com.roomfit.be.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "messages")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", insertable = false, updatable = false)
    @ToString.Exclude
    private User sender;

    @ManyToOne(targetEntity = ChatRoom.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id", insertable = false, updatable = false)
    @ToString.Exclude
    private ChatRoom chatRoom;

    @Column(name = "chatroom_id")
    private Long chatRoomId;

    @Column(name = "sender_id")
    private Long senderId;

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public static Message create(Long senderId,  Long chatRoomId, String content) {
        return Message.builder()
                .content(content)
                .senderId(senderId)
                .chatRoomId(chatRoomId)
                .build();
    }
}
