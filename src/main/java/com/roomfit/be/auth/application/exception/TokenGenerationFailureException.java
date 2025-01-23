package com.roomfit.be.auth.application.exception;

import com.roomfit.be.global.exception.ApplicationException;
import com.roomfit.be.global.exception.ErrorCode;

public class TokenGenerationFailureException extends ApplicationException {
    public TokenGenerationFailureException() {
        super(ErrorCode.AUTH_TOKEN_GENERATION_FAILURE);
    }
}
