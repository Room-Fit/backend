package com.roomfit.be.chat.application;


import com.roomfit.be.chat.application.dto.ChatRoomDTO;
import com.roomfit.be.chat.application.dto.MessageDTO;
import com.roomfit.be.chat.domain.Message;

import java.util.List;

public interface ChatRoomService {
    ChatRoomDTO.Response createRoom(Long userId, ChatRoomDTO.Create request);
    void enterRoom(Long userId,Long roomId);
    void leaveRoom(ChatRoomDTO.Leave request);
    List<MessageDTO.Response> readMessageByRoomId(Long roomId);

    List<ChatRoomDTO.Response> readAllChatRooms(String type);
}
