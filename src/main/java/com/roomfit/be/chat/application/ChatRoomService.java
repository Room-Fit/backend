package com.roomfit.be.chat.application;


import com.roomfit.be.chat.application.dto.ChatRoomDTO;
import com.roomfit.be.chat.application.dto.MessageDTO;
import com.roomfit.be.global.response.PaginationResponse;

import java.util.List;

public interface ChatRoomService {
    ChatRoomDTO.Response createRoom(Long userId, ChatRoomDTO.Create request);
    void enterRoom(Long userId,Long roomId);
    void leaveRoom(ChatRoomDTO.Leave request);

    PaginationResponse<MessageDTO.Response>  readMessageByRoomId(Long roomId, Long lastMessageId, int pageSize);
    List<ChatRoomDTO.Response> readAllChatRooms(String type);
}
