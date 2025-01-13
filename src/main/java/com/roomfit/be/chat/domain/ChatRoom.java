package com.roomfit.be.chat.domain;

import com.roomfit.be.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="chatrooms")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @Enumerated(EnumType.STRING)
    ChatRoomType type;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    ChatRoomStatus status = ChatRoomStatus.RECRUITING;

    Integer maxQuota;

    @Builder.Default
    Integer currentQuota = 1;
    public static ChatRoom createPrivateRoom(String name){
        return ChatRoom.builder()
                .type(ChatRoomType.PRIVATE)
                .maxQuota(2)
                .name(name)
                .build();
    }
    public static ChatRoom createGroupRoom(String name, Integer maxQuota){
        return ChatRoom.builder()
                .type(ChatRoomType.GROUP)
                .maxQuota(maxQuota)
                .name(name)
                .build();
    }
}
