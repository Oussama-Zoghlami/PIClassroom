package com.comeback.securityauthback.controller;

import com.comeback.securityauthback.entities.SchoolClass;
import com.comeback.securityauthback.services.Services;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth/adminpage")
public class Controllers {

    private final Services services;
    @PostMapping("/addclass")
    public SchoolClass addClass(@RequestBody SchoolClass schoolClass){
        SchoolClass s = services.addClass(schoolClass);
        return s;
    }
}
