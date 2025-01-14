package com.roomfit.be.message.application;

import com.roomfit.be.message.application.dto.MessageDTO;
import com.roomfit.be.message.domain.Message;
import com.roomfit.be.message.infrastructure.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements  MessageService{
    private final MessageRepository messageRepository;
    @Override
    public List<MessageDTO.Response> readMessage() {
        List<Message> foundMessage =  messageRepository.findAll();
        return foundMessage.stream()
                .map(MessageDTO.Response::of)
                .toList();
    }
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
