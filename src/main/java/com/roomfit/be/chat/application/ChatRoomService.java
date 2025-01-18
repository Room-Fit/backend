package com.roomfit.be.chat.application;


import com.roomfit.be.chat.application.dto.ChatRoomDTO;

import java.util.List;

public interface ChatRoomService {
    ChatRoomDTO.Response createRoom(Long userId, ChatRoomDTO.Create request);
    ChatRoomDTO.Response enterRoom(Long userId,Long roomId);
    void leaveRoom(ChatRoomDTO.Leave request);
    List<ChatRoomDTO.Response> readMessageByUserId(Long userId);
}
