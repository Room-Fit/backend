package com.roomfit.be.chat.application.chatroom;

import java.util.List;

public interface ChatRoomService {
    ChatRoomDTO.Response createRoom(ChatRoomDTO.Create request);
    ChatRoomDTO.Response enterRoom(ChatRoomDTO.Enter request);
    void leaveRoom(ChatRoomDTO.Leave request);
    List<ChatRoomDTO.Response> readMessageByUserId(Long userId);
}
