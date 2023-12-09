package com.comeback.securityauthback.services;

import com.comeback.securityauthback.dto.JwtAuthenticationResponse;
import com.comeback.securityauthback.dto.RefreshTokenRequest;
import com.comeback.securityauthback.dto.SignUpRequest;
import com.comeback.securityauthback.dto.SigninRequest;
import com.comeback.securityauthback.entities.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
