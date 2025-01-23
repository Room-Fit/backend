package com.roomfit.be.auth.application;

import com.roomfit.be.auth.application.dto.AuthDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
    private final AuthenticationService authenticationService;
    private final VerificationService verificationService;

    @Override
    public AuthDTO.LoginResponse authenticate(AuthDTO.Login request) {
        return authenticationService.authenticate(request);
    }

    @Override
    public boolean checkEmailVerificationStatus(String authToken) {
        return verificationService.getStatus(authToken);
    }

    @Async("taskExecutor")
    @Override
    public void generateVerificationCode(String authToken, String email) {
        verificationService.generateVerificationCode(authToken, email);
    }

    @Override
    public boolean verifyVerificationCode(String authToken, String code) {
        return verificationService.verifyVerificationCode(authToken, code);
    }
}
