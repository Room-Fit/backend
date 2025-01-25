package com.roomfit.be.chat.application.dto;

import com.roomfit.be.chat.domain.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ChatRoomDTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        Long id;
        String name;
        String description;
        String type;
        String status;
        Integer maxQuota;
        Integer currentQuota;
        String dormitory;
        public static Response of(ChatRoom chatRoom){
            return Response.builder()
                    .id(chatRoom.getId())
                    .name(chatRoom.getName())
                    .type(chatRoom.getType().name())
                    .status(chatRoom.getStatus().name())
                    .maxQuota(chatRoom.getMaxQuota())
                    .currentQuota(chatRoom.getCurrentQuota())
                    .dormitory(chatRoom.getDormitory())
                    .description(chatRoom.getDescription())
                    .build();
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create{
        String name;
        String description;
        String type;
        Integer maxQuota;
        String dormitory;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Leave{
        Long userId;
        Long roomId;
    }

}
