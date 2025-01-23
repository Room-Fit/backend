package com.roomfit.be.global.response;

public record CommonResponse<T>(boolean success, String message, T data) {
    public CommonResponse(boolean success, String message) {
        this(success, message, null);
    }
}