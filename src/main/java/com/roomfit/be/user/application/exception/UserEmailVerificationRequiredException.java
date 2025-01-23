package com.roomfit.be.user.application.exception;

import com.roomfit.be.global.exception.ApplicationException;
import com.roomfit.be.global.exception.ErrorCode;

public class UserEmailVerificationRequiredException extends ApplicationException {
    public UserEmailVerificationRequiredException() {
        super(ErrorCode.USER_EMAIL_VERIFICATION_REQUIRED);
    }
}
