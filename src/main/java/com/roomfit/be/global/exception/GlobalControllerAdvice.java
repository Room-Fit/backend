package com.roomfit.be.global.exception;

import com.roomfit.be.global.response.CommonResponse;
import com.roomfit.be.global.response.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<CommonResponse<Void>> handleApplicationException(ApplicationException ex) {
        CommonResponse<Void> commonResponse = ResponseFactory.failure(ex.getMessage());
        return ResponseEntity
                .status(ex.getStatus())
                .body(commonResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse<Void> handleGeneralException(Exception ex) {
        return ResponseFactory.failure("서버에서 예상치 못한 오류가 발생했습니다.");
    }
}
