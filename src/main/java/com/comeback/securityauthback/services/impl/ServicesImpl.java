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

import java.util.*;

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

    @Override
    public void affectSubjectClass(Integer idSubject, Integer idClass) {
        SchoolClass s = schoolClassRepo.findById(idClass).get();
        Subject c = subjectRepo.findById(idSubject).get();

        c.setSchoolClass(s);
        schoolClassRepo.save(s);

    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepo.save(event);
    }

    @Override
    public SchoolClass updateClass(SchoolClass schoolClass) {
        return schoolClassRepo.save(schoolClass);
    }

    @Override
    public Subject updateSubject(Subject subject) {
        return subjectRepo.save(subject);
    }
    @Override
    public User findUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
    }
    @Override
    public void addAbsenceToUser(Integer userId, Integer idSubject) {
        // Retrieve the user from the database
        Optional<User> userOptional = userRepository.findById(userId);

        // Check if the user exists
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Increment the absence for the specific subject
            user.incrementSubjectAbsence(idSubject); // Assuming you have a method in the User class for this

            // Save the updated user back to the database
            userRepository.save(user);
        } else {
            // Handle the case where the user with the given ID is not found
            throw new EntityNotFoundException("User not found with ID: " + userId);
        }
    }
    @Override
    public void displayAbsencesAndSubjects(Integer userId) {
        // Retrieve the user from the database
        Optional<User> userOptional = userRepository.findById(userId);

        // Check if the user exists
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Get the set of subjects associated with the user
            Set<Subject> subjects = user.getSubjects();

            // Print the user's name
            System.out.println("User: " + user.getFirstname() + " " + user.getLastname());

            // Iterate through the subjects and display absences and names
            for (Subject subject : subjects) {
                System.out.println("Subject: " + subject.getTitle() + ", Absences: " + subject.getAbsence());
            }
        } else {
            // Handle the case where the user with the given ID is not found
            throw new EntityNotFoundException("User not found with ID: " + userId);
        }
    }
    @Override
    public Subject getSubjectDetailsById(Integer idSubject) {
        return subjectRepo.findById(idSubject).orElse(null);
    }
    @Override
    public List<User> getUsersByClassId(Integer classId) {
        return userRepository.findBySchoolClass_IdClass(classId);
    }
    @Override
    public List<Subject> getSubjectByClassId(Integer classId) {
        return subjectRepo.findBySchoolClass(classId);
    }

    @Override
    public List<String> getUsersByClassRole(Role role, Integer classId) {
        SchoolClass schoolClass = schoolClassRepo.findById(classId).orElse(null);
        if (schoolClass == null) {
            // Handle the case where the class is not found, you might want to throw an exception or return an empty list
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();
        for (User user : userRepository.findBySchoolClass_IdClass(classId)) {
            if (user.getRole() == role) {
                // Assuming 'role' is an Enum, adjust the condition accordingly
                result.add(user.getFirstname() + " " + user.getLastname() + " - " + user.getEmail() + " (" + user.getRole() + ")");
            }
        }

        return result;
    }



}
