package com.roomfit.be.message.application;


import com.roomfit.be.message.application.dto.MessageDTO;

import java.util.List;

public interface MessageService {
    List<MessageDTO.Response> readMessage();
    MessageDTO.Response sendMessage(MessageDTO.Send request);
}
