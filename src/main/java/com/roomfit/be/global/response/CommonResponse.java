package com.roomfit.be.global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonResponse<T> {
    private boolean success;  // 요청 성공 여부
    private String message;   // 응답 메시지
    private T data;           // 실제 데이터
}