package com.comeback.securityauthback.services.impl;

import com.comeback.securityauthback.entities.*;
import com.comeback.securityauthback.repository.EventRepo;
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
    @Autowired
    EventRepo eventRepo;




    @Override
    public SchoolClass addClass(SchoolClass schoolClass) {
        return schoolClassRepo.save(schoolClass);
    }

    @Override
    public SchoolClass affichClass(Integer idClass) {
        return schoolClassRepo.findById(idClass).orElse(null);
    }

    @Override
    public void deleteClass(Integer idClass) {
        schoolClassRepo.deleteById(idClass);

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



    @Override
    public Event addEvent(Event event) {
        return eventRepo.save(event);
    }

    @Override
    public Event affichEvent(Long idEvent) {
        return eventRepo.findById(idEvent).orElse(null);
    }

    @Override
    public void deleteEvent(Long idEvent) {
        eventRepo.deleteById(idEvent);

    }

    @Override
    public void affectUtilisateurClass(Integer idUser, Integer idClass) {
        User u = userRepository.findById(idUser).get();
        SchoolClass c = schoolClassRepo.findById(idClass).get();

        u.setSchoolClass(c);
        userRepository.save(u);


    }

    @Override
    public void affectUtilisateurSubject(Integer idUser, Integer idSubject) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + idUser));
        Subject subject = subjectRepo.findById(idSubject).orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + idSubject));

        // Assuming that the relationship between User and Subject is Many-to-Many
        user.getSubjects().add(subject);
        userRepository.save(user);
    }


}
