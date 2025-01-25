package com.roomfit.be.chat.application;

import com.roomfit.be.chat.application.dto.ChatRoomDTO;
import com.roomfit.be.chat.application.dto.MessageDTO;
import com.roomfit.be.chat.domain.ChatRoom;
import com.roomfit.be.chat.domain.ChatRoomType;
import com.roomfit.be.chat.domain.Message;
import com.roomfit.be.chat.infrastructure.ChatRoomRepository;
import com.roomfit.be.global.response.PaginationResponse;
import com.roomfit.be.global.event.EventPublisher;
import com.roomfit.be.participation.application.event.JoinAsHostEvent;
import com.roomfit.be.participation.application.event.JoinAsParticipantEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRepository;
    private final EventPublisher eventPublisher;
    @Override
    public ChatRoomDTO.Response createRoom(Long userId, ChatRoomDTO.Create request) {
        ChatRoom chatRoom = switch (ChatRoomType.fromString(request.getType())) {
            case GROUP -> createGroupRoom(request);
            case PRIVATE -> createPrivateRoom(request);
        };
        ChatRoom savedChatRoom =  chatRepository.save(chatRoom);
        eventPublisher.publish(JoinAsHostEvent.of(this, userId, savedChatRoom.getId()));
        return ChatRoomDTO.Response.of(savedChatRoom);
    }

    @Override
    public void enterRoom(Long userId, Long roomId) {
        eventPublisher.publish(JoinAsParticipantEvent.of(this, userId, roomId));
    }

    @Override
    public void leaveRoom(ChatRoomDTO.Leave request) {

    }

    /**
     * TODO : UserID 형식으로 변경되게
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationResponse<MessageDTO.Response> readMessageByRoomId(Long roomId, Long lastMessageId, int pageSize) {
        PaginationResponse<Message> messages =  chatRepository.findMessagesByChatRoomIdPaginated(roomId, lastMessageId, pageSize);
        List<MessageDTO.Response> messageDTOs = messages.getData().stream()
                .map(MessageDTO.Response::of)
                .toList();
        return new PaginationResponse<>(messageDTOs, messages.getTotalCount(), messages.isHasNext());
    }

    @Override
    public List<ChatRoomDTO.Response> readAllChatRooms(String type) {
        ChatRoomType chatRoomType = ChatRoomType.fromString(type);
        return chatRepository.findByType(chatRoomType).stream()
                .map(ChatRoomDTO.Response::of)
                .toList();
    }

    private ChatRoom createGroupRoom(ChatRoomDTO.Create request){
        return ChatRoom.createGroupRoom(request.getName(), request.getDescription(), request.getDormitory(), request.getMaxQuota());
    }
    private ChatRoom createPrivateRoom(ChatRoomDTO.Create request){
        return ChatRoom.createPrivateRoom(request.getName());
    }
}
