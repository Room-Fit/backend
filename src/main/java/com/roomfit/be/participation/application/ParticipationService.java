package com.roomfit.be.participation.application;

import com.roomfit.be.chat.domain.ChatRoom;
import com.roomfit.be.user.domain.User;

import java.util.List;

public interface ParticipationService {
    void joinAsHost(Long userId, Long chatRoomId);
    void joinAsParticipant(Long userId, Long chatRoomId);
//    void removeParticipant(Long userId, Long chatRoomId);
    List<User> readParticipantsByRoomId(Long roomId);
}
