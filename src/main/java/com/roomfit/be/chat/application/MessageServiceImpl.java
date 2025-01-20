package com.roomfit.be.chat.application;

import com.roomfit.be.chat.application.dto.MessageDTO;
import com.roomfit.be.chat.domain.Message;
import com.roomfit.be.chat.infrastructure.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MessageEventService로 정정
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements  MessageService{
    private final MessageRepository messageRepository;
    @Transactional
    @Override
    public MessageDTO.Response sendMessage(MessageDTO.Send request) {
        Long senderId = request.getSender().getUserId();
        Long roomId = request.getRoomId();
        String content = request.getContent();
        String senderNickname = request.getSender().getNickname();

        Message newMessage = Message.create(senderId, roomId, content);
        Message savedMessage = messageRepository.save(newMessage);

        return MessageDTO.Response.of(senderNickname, savedMessage);
    }

    public List<MessageDTO.Response> saveBulkMessage(List<MessageDTO.Send> request) {
        return null;
    }


}
