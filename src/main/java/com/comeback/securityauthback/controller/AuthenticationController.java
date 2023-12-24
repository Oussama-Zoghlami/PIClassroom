package com.comeback.securityauthback.controller;

import com.comeback.securityauthback.dto.JwtAuthenticationResponse;
import com.comeback.securityauthback.dto.RefreshTokenRequest;
import com.comeback.securityauthback.dto.SignUpRequest;
import com.comeback.securityauthback.dto.SigninRequest;
import com.comeback.securityauthback.entities.User;
import com.comeback.securityauthback.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins="*")

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
        System.out.println("Received signup request: " + signUpRequest);
        User user = authenticationService.signup(signUpRequest);
        System.out.println("User created: " + user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest) {
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

}

