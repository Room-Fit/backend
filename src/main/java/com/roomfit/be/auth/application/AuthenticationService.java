package com.roomfit.be.auth.application;

import com.roomfit.be.auth.application.dto.AuthDTO;

public interface AuthenticationService {
    AuthDTO.LoginResponse authenticate(AuthDTO.Login request);

    AuthDTO.LoginResponse reissueRefreshToken(String email, String refresh);
}
