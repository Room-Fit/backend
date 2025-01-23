package com.roomfit.be.global.response;

public class ResponseFactory {

    // 성공 응답 (데이터 포함 + 커스텀 코드)
    public static <T> CommonResponse<T> success(T data, String message) {
        return new CommonResponse<>(true, message, data);

    }

    public static <T> CommonResponse<T> success(String message) {
        return success(null, message );
    }
    // 실패 응답
    public static CommonResponse<Void> failure(String message) {
        return new CommonResponse<>(false, message);
    }
}
