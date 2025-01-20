package com.roomfit.be.chat.application;


import com.roomfit.be.chat.application.dto.MessageDTO;

public interface MessageService {
    MessageDTO.Response sendMessage(MessageDTO.Send request);
}
