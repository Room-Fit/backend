package com.roomfit.be.auth.presentation;

import com.roomfit.be.auth.application.AuthService;
import com.roomfit.be.auth.application.dto.AuthDTO;
import com.roomfit.be.global.response.CommonResponse;
import com.roomfit.be.global.response.ResponseFactory;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<CommonResponse<AuthDTO.LoginResponse>> login(
            @RequestBody @Parameter(description = "로그인 정보") AuthDTO.Login request) {
        AuthDTO.LoginResponse response = authService.authenticate(request);
        CommonResponse<AuthDTO.LoginResponse> commonResponse = ResponseFactory.success(response, "로그인 성공");
        return ResponseEntity
                .status(HttpStatus.OK)  // 200 OK
                .body(commonResponse);  // 응답 본문
    }

    /**
     * 인증 코드 전송
     */
    @PostMapping("/code")
    public ResponseEntity<CommonResponse<String>> sendVerificationCode(
            @RequestBody @Parameter(description = "이메일") AuthDTO.SendCodeRequest request) {
        String authToken = UUID.randomUUID().toString();
        authService.generateVerificationCode(authToken, request.getEmail());
        CommonResponse<String> commonResponse = ResponseFactory.success(authToken, "인증 코드 전송 성공");
        return ResponseEntity
                .status(HttpStatus.OK)  // 200 OK
                .body(commonResponse);  // 응답 본문
    }

    /**
     * 인증 코드 검증
     */
    @PostMapping("/verify")
    public ResponseEntity<CommonResponse<Boolean>> verifyVerificationCode(
            @RequestBody @Parameter(description = "인증 코드 요청 정보") AuthDTO.VerifyCodeRequest request) {
        boolean isVerified = authService.verifyVerificationCode(request.getAuthToken(), request.getCode());
        CommonResponse<Boolean> commonResponse = ResponseFactory.success(isVerified, isVerified ? "인증 성공" : "인증 실패");
        return ResponseEntity
                .status(HttpStatus.OK)  // 200 OK
                .body(commonResponse);  // 응답 본문
    }
}
