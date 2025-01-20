package com.roomfit.be.chat.presentation;

import com.roomfit.be.chat.application.MessageService;
import com.roomfit.be.chat.application.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MessageEventController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/send/{roomId}")
    public void sendMessage(@DestinationVariable Long roomId, MessageDTO.Send message) {
        messagingTemplate.convertAndSend("/topic/room/" + roomId, message);
        messageService.sendMessage(message);
    }
}
