package com.roomfit.be.survey.application.exception;

import com.roomfit.be.global.exception.ApplicationException;
import com.roomfit.be.global.exception.ErrorCode;

public class ReplySaveFailureException extends ApplicationException {
    public ReplySaveFailureException() {
        super(ErrorCode.REPLY_SAVE_FAILURE);
    }
}
