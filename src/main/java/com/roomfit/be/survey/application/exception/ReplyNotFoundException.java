package com.roomfit.be.survey.application.exception;

import com.roomfit.be.global.exception.ApplicationException;
import com.roomfit.be.global.exception.ErrorCode;

public class ReplyNotFoundException extends ApplicationException {
    public ReplyNotFoundException() {
        super(ErrorCode.REPLY_NOT_FOUND);
    }
}
