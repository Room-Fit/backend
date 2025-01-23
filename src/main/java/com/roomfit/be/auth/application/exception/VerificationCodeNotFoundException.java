package com.roomfit.be.auth.application.exception;

import com.roomfit.be.global.exception.ApplicationException;
import com.roomfit.be.global.exception.ErrorCode;

public class VerificationCodeNotFoundException extends ApplicationException {
    public VerificationCodeNotFoundException() {
        super(ErrorCode.AUTH_VERIFICATION_CODE_NOT_FOUND);
    }
}
