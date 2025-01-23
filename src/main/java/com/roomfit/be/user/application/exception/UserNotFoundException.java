package com.roomfit.be.user.application.exception;

import com.roomfit.be.global.exception.ApplicationException;
import com.roomfit.be.global.exception.ErrorCode;

public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
