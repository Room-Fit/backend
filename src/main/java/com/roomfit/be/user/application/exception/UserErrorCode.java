package com.roomfit.be.user.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum UserErrorCode {
    NOT_FOUND(404, "유저를 찾을 수 없습니다"),
    EMAIL_VERIFICATION_REQUIRED(400, "이메일 인증을 해주세요");

    private final int statusCode;
    private final String message;
}

