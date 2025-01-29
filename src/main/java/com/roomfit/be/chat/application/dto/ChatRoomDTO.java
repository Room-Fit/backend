package com.roomfit.be.chat.application.dto;

import com.roomfit.be.chat.domain.ChatRoom;
import com.roomfit.be.participation.application.dto.ParticipantDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailsResponse {

        Long id;
        String name;
        String description;
        String type;
        String status;
        Integer maxQuota;
        Integer currentQuota;
        String dormitory;
        List<ParticipantDTO> participants;

        public static DetailsResponse of(ChatRoom chatRoom, List<ParticipantDTO> participants) {
            return DetailsResponse.builder()
                    .id(chatRoom.getId())
                    .name(chatRoom.getName())
                    .description(chatRoom.getDescription())
                    .type(chatRoom.getType().name())
                    .status(chatRoom.getStatus().name())
                    .maxQuota(chatRoom.getMaxQuota())
                    .currentQuota(chatRoom.getCurrentQuota())
                    .dormitory(chatRoom.getDormitory())
                    .participants(participants)
                    .build();
        }
    }
}
