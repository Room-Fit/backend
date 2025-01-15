package com.roomfit.be.auth.application;

public interface AuthService {
    void generateVerificationCode(String sessionId, String email);
    boolean verifyVerificationCode(String sessionId, String code);
}
