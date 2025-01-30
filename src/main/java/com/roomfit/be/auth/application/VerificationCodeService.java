package com.roomfit.be.auth.application;

public interface VerificationCodeService {
    void generateVerificationCode(String authToken, String email);
    boolean verifyVerificationCode(String authToken, String code);
    boolean getStatus(String authToken);
}
