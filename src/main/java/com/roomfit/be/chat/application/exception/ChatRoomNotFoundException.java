package com.roomfit.be.chat.application.exception;

import com.roomfit.be.global.exception.ApplicationException;
import com.roomfit.be.global.exception.ErrorCode;

public class ChatRoomNotFoundException extends ApplicationException {
    public ChatRoomNotFoundException() {
        super(ErrorCode.CHATROOM_NOT_FOUND);
    }
}
