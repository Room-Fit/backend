package com.roomfit.be.auth.presentation;

import com.roomfit.be.auth.application.AuthService;
import com.roomfit.be.auth.application.dto.AuthDTO;
import com.roomfit.be.global.response.CommonResponse;
import com.roomfit.be.global.response.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @Operation(
            summary = "로그인",
            description = "사용자가 제공한 로그인 정보로 인증을 수행합니다."
    )
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)  // Simplified status code handling with @ResponseStatus
    public CommonResponse<AuthDTO.LoginResponse> login(
            @RequestBody @Parameter(description = "로그인 정보") AuthDTO.Login request) {
        AuthDTO.LoginResponse response = authService.authenticate(request);
        return ResponseFactory.success(response, "로그인 성공");
    }

    /**
     * 토큰 재발급
     */
    @Operation(
            summary = "토큰 재발급",
            description = "토큰 재발급을 처리합니다."
    )
    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)  // Simplified status code handling with @ResponseStatus
    public CommonResponse<AuthDTO.LoginResponse> refreshToken(
            @RequestBody @Parameter(description = "이메일") AuthDTO.Reissue request) {
        AuthDTO.LoginResponse response = authService.reissueRefreshToken(request);
        return ResponseFactory.success(response, "토큰 재발급 성공");
    }

    /**
     * 인증 코드 전송
     */
    @Operation(
            summary = "인증 코드 전송",
            description = "이메일로 인증 코드를 전송합니다."
    )
    @PostMapping("/code")
    @ResponseStatus(HttpStatus.OK)  // Simplified status code handling with @ResponseStatus
    public CommonResponse<String> sendVerificationCode(
            @RequestBody @Parameter(description = "이메일") AuthDTO.SendCodeRequest request) {
        String authToken = UUID.randomUUID().toString();
        authService.generateVerificationCode(authToken, request.getEmail());
        return ResponseFactory.success(authToken, "인증 코드 전송 성공");
    }

    /**
     * 인증 코드 검증
     */
    @Operation(
            summary = "인증 코드 검증",
            description = "사용자가 제공한 인증 코드가 유효한지 검증합니다."
    )
    @PostMapping("/code/verify")
    @ResponseStatus(HttpStatus.OK)  // Simplified status code handling with @ResponseStatus
    public CommonResponse<Boolean> verifyVerificationCode(
            @RequestBody @Parameter(description = "인증 코드 요청 정보") AuthDTO.VerifyCodeRequest request) {
        boolean isVerified = authService.verifyVerificationCode(request.getAuthToken(), request.getCode());
        return ResponseFactory.success(isVerified, isVerified ? "인증 성공" : "인증 실패");
    }
}
