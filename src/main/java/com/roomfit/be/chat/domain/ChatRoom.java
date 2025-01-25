package com.roomfit.be.chat.domain;

import com.roomfit.be.global.entity.BaseEntity;
import com.roomfit.be.participation.domain.Participation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    String description;

    @Enumerated(EnumType.STRING)
    ChatRoomType type;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    ChatRoomStatus status = ChatRoomStatus.RECRUITING;

    Integer maxQuota;

    String dormitory;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chatRoom")
    @Builder.Default
    private List<Participation> participationList = new ArrayList<>();
    public Integer getCurrentQuota() {
        if(participationList != null){
            return participationList.size();
        }
        return 0;
    }

    public static ChatRoom createPrivateRoom(String name){
        return ChatRoom.builder()
                .type(ChatRoomType.PRIVATE)
                .maxQuota(DEFAULT_PRIVATE_MAX_QUOTA)
                .name(name)
                .build();
    }
    public static ChatRoom createGroupRoom(String name, String description, String dormitory, Integer maxQuota){
        return ChatRoom.builder()
                .type(ChatRoomType.GROUP)
                .maxQuota(maxQuota)
                .name(name)
                .description(description)
                .dormitory(dormitory)
                .build();
    }
}
