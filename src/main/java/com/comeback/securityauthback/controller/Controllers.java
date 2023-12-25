package com.comeback.securityauthback.controller;

import com.comeback.securityauthback.entities.Role;
import com.comeback.securityauthback.entities.SchoolClass;
import com.comeback.securityauthback.entities.Subject;
import com.comeback.securityauthback.entities.User;
import com.comeback.securityauthback.services.Services;
import lombok.AllArgsConstructor;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth/adminpage")
public class Controllers {

    private final Services services;

    @PreAuthorize("permitAll()")
    @PostMapping("/addclass")
    public SchoolClass addClass(@RequestBody SchoolClass schoolClass){
        SchoolClass s = services.addClass(schoolClass);
        return s;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/usersByRole/{role}")
    public List<User> getUsersByRole(@PathVariable Role role) {
        return services.getUsersByRole(role);
    }
    @PreAuthorize("permitAll()")
    @PostMapping("/addAbsence/{userId}")
    public void addAbsenceToUser(@PathVariable Integer userId) {
        services.addAbsenceToUser(userId);
    }
    @PreAuthorize("permitAll()")
    @PostMapping("/addsubject")
    public Subject addSubject(@RequestBody Subject subject){
        Subject s = services.addSubject(subject);
        return s;
    }

}
