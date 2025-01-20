package com.roomfit.be.chat.application.dto;

import com.roomfit.be.chat.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MessageDTO {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        Long id;
        String content;
        String sender;
        LocalDateTime createdAt;
        public static Response of(String sender, Message message){
            return Response.builder()
                    .id(message.getId())
                    .content(message.getContent())
                    .sender(sender)
                    .createdAt(message.getCreatedAt())
                    .build();
        }
        public static Response of(Message message){
            return Response.builder()
                    .id(message.getId())
                    .content(message.getContent())
                    .sender(message.getSender().getNickname())
                    .createdAt(message.getCreatedAt())
                    .build();
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Send{
        Long roomId;
        Sender sender;
        String content;
    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sender{
        Long userId;
        String nickname;
    }
}
