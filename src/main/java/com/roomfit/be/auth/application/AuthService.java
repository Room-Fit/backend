package com.roomfit.be.auth.application;

import com.roomfit.be.auth.application.dto.AuthDTO;

public interface AuthService {
    void generateVerificationCode(String sessionId, String email);
    boolean verifyVerificationCode(String sessionId, String code);
    AuthDTO.LoginResponse authenticate(AuthDTO.Login request);
}
