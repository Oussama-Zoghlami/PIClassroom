package com.comeback.securityauthback.services;

import com.comeback.securityauthback.entities.Role;
import com.comeback.securityauthback.entities.SchoolClass;
import com.comeback.securityauthback.entities.Subject;
import com.comeback.securityauthback.entities.User;

import java.util.List;

public interface Services {
    public SchoolClass addClass(SchoolClass schoolClass);
    List<User> getUsersByRole(Role role);

    void addAbsenceToUser(Integer userId);
    public Subject addSubject(Subject subject);


}
