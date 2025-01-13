package com.roomfit.be.chat.domain;

public enum ChatRoomType {
    GROUP,
    PRIVATE;
    public static ChatRoomType fromString(String type) {
        try {
            return ChatRoomType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid chat room type: " + type, e);
        }
    }
}
