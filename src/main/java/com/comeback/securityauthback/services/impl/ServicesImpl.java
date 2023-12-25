package com.comeback.securityauthback.services.impl;

import com.comeback.securityauthback.entities.Role;
import com.comeback.securityauthback.entities.SchoolClass;
import com.comeback.securityauthback.entities.Subject;
import com.comeback.securityauthback.entities.User;
import com.comeback.securityauthback.repository.SchoolClassRepo;
import com.comeback.securityauthback.repository.SubjectRepo;
import com.comeback.securityauthback.repository.UserRepository;
import com.comeback.securityauthback.services.Services;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class ServicesImpl implements Services {

    @Autowired
    SchoolClassRepo schoolClassRepo;
    @Autowired
    UserRepository userRepository;

    @Autowired
    SubjectRepo subjectRepo;




    @Override
    public SchoolClass addClass(SchoolClass schoolClass) {
        return schoolClassRepo.save(schoolClass);
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    public void addAbsenceToUser(Integer userId) {
        //  Retrieve the user from the database
        Optional<User> userOptional = userRepository.findById(userId);

        //  Check if the user exists and update the absence
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.incrementAllSubjectsAbsence(); // Assuming you have a method to increment absence in the User class

            //  Save the updated user back to the database
            userRepository.save(user);
        } else {
            // Handle the case where the user with the given ID is not found
            throw new EntityNotFoundException("User not found with ID: " + userId);
        }
    }

    @Override
    public Subject addSubject(Subject subject) {
        return subjectRepo.save(subject);
    }


}
