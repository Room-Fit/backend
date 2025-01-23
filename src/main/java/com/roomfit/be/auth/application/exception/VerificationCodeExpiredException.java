package com.roomfit.be.auth.application.exception;

import com.roomfit.be.global.exception.ApplicationException;
import com.roomfit.be.global.exception.ErrorCode;

public class VerificationCodeExpiredException extends ApplicationException {
    public VerificationCodeExpiredException() {
        super(ErrorCode.AUTH_VERIFICATION_CODE_EXPIRED);
    }
}
