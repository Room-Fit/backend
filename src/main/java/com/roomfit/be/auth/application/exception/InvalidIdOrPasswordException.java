package com.roomfit.be.auth.application.exception;

import com.roomfit.be.global.exception.ApplicationException;
import com.roomfit.be.global.exception.ErrorCode;

public class InvalidIdOrPasswordException extends ApplicationException {
    public InvalidIdOrPasswordException() {
        super(ErrorCode.AUTH_INVALID_ID_OR_PASSWORD);
    }
}
