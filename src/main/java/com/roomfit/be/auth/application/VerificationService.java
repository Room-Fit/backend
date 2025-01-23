package com.roomfit.be.auth.application;

public interface VerificationService {
    void generateVerificationCode(String authToken, String email);
    boolean verifyVerificationCode(String authToken, String code);
    boolean getStatus(String authToken);
}
