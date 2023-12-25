package com.comeback.securityauthback.services.impl;

import com.comeback.securityauthback.entities.SchoolClass;
import com.comeback.securityauthback.entities.User;
import com.comeback.securityauthback.repository.SchoolClassRepo;
import com.comeback.securityauthback.repository.UserRepository;
import com.comeback.securityauthback.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesImpl implements Services {

    @Autowired
    SchoolClassRepo schoolClassRepo;



    @Override
    public SchoolClass addClass(SchoolClass schoolClass) {
        return schoolClassRepo.save(schoolClass);
    }


}
