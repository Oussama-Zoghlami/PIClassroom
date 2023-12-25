package com.comeback.securityauthback.controller;

import com.comeback.securityauthback.entities.SchoolClass;
import com.comeback.securityauthback.services.Services;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final Services services;

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("hi Admin");
    }



}
