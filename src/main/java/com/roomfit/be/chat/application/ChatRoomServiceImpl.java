package com.roomfit.be.chat.application;

import com.roomfit.be.chat.application.dto.ChatRoomDTO;
import com.roomfit.be.chat.application.dto.MessageDTO;
import com.roomfit.be.chat.domain.ChatRoom;
import com.roomfit.be.chat.domain.ChatRoomType;
import com.roomfit.be.chat.infrastructure.ChatRoomRepository;
import com.roomfit.be.participation.application.ParticipationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRepository;
    private final ParticipationService participationService;
    @Override
    public ChatRoomDTO.Response createRoom(Long userId, ChatRoomDTO.Create request) {
        ChatRoom chatRoom = switch (ChatRoomType.fromString(request.getType())) {
            case GROUP -> createGroupRoom(request);
            case PRIVATE -> createPrivateRoom(request);
        };
        ChatRoom savedChatRoom =  chatRepository.save(chatRoom);
        participationService.joinAsHost(userId, savedChatRoom.getId());

        return ChatRoomDTO.Response.of(savedChatRoom);
    }

    @Override
    public ChatRoomDTO.Response enterRoom(Long userId, Long roomId) {
        ChatRoom enteredRoom= participationService.joinAsParticipant(userId, roomId);
        return ChatRoomDTO.Response.of(enteredRoom);
    }

    @Override
    public void leaveRoom(ChatRoomDTO.Leave request) {

    }

    /**
     * TODO : UserID 형식으로 변경되게
     */
    @Transactional
    @Override
    public List<MessageDTO.Response> readMessageByRoomId(Long roomId) {
        return chatRepository.findMessagesByChatRoomId(roomId).stream()
                .map(MessageDTO.Response::of)
                .toList();
    }

    private ChatRoom createGroupRoom(ChatRoomDTO.Create request){
        return ChatRoom.createGroupRoom(request.getName(), request.getDescription(), request.getDormitory(), request.getMaxQuota());
    }
    private ChatRoom createPrivateRoom(ChatRoomDTO.Create request){
        return ChatRoom.createPrivateRoom(request.getName());
    }
}
