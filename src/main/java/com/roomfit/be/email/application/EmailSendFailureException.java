package com.roomfit.be.email.application;

import com.roomfit.be.global.exception.ApplicationException;
import com.roomfit.be.global.exception.ErrorCode;

public class EmailSendFailureException extends ApplicationException {
    public EmailSendFailureException() {
        super(ErrorCode.EMAIL_SEND_FAILURE);
    }
}
