package com.roomfit.be.auth.presentation;

import com.roomfit.be.auth.application.AuthService;
import com.roomfit.be.auth.application.dto.AuthDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @PostMapping("/login")
    AuthDTO.LoginResponse login(@RequestBody AuthDTO.Login request) {

        return authService.authenticate(request);
    }

    /**
     * 이미 가입된 이메일은 접근이 불가하게
     */
    @PostMapping("/code")
    String sendVerificationCode(@RequestBody AuthDTO.SendCodeRequest request) {
        String authToken = UUID.randomUUID().toString();
        authService.generateVerificationCode(authToken, request.getEmail());
        return authToken;
    }

    @PostMapping("/verify")
        boolean verifyVerificationCode(@RequestBody AuthDTO.VerifyCodeRequest request) {
        return authService.verifyVerificationCode(request.getAuthToken(), request.getCode());

    }
}
