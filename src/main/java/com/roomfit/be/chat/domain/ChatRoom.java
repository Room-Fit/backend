package com.roomfit.be.chat.domain;

import com.roomfit.be.global.entity.BaseEntity;
//import com.roomfit.be.participation.Participation;
import com.roomfit.be.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * TODO: 방장 기능 추가
 */
@Entity(name="chatrooms")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom extends BaseEntity {
    private final static Integer DEFAULT_QUOTA = 1;
    private final static Integer DEFAULT_PRIVATE_MAX_QUOTA = 2;
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
    Integer currentQuota = DEFAULT_QUOTA;


//    @OneToMany()
//    List<Participation> participationList;

    public static ChatRoom createPrivateRoom(String name){
        return ChatRoom.builder()
                .type(ChatRoomType.PRIVATE)
                .maxQuota(DEFAULT_PRIVATE_MAX_QUOTA)
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
