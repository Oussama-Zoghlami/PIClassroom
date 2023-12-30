package com.comeback.securityauthback.services.impl;

import com.comeback.securityauthback.entities.*;
import com.comeback.securityauthback.repository.*;
import com.comeback.securityauthback.services.Services;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    NoteRepo noteRepo ;




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
    public Subject getSubjectDetailsById(Integer idSubject) {
        return subjectRepo.findById(idSubject).orElse(null);
    }
    @Override
    public List<User> getUsersByClassId(Integer classId) {
        return userRepository.findBySchoolClass_IdClass(classId);
    }
    @Override
    public List<Subject> getSubjectByClassId(Integer classId) {
        return subjectRepo.findBySchoolClass_IdClass(classId);
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
    @Override
    public void addNoteWithParams(Long userId, Integer subjectId, Double noteValue) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Note note = new Note();
        note.setUser(user);
        note.setSubject(subject);
        note.setValue(noteValue);
        noteRepo.save(note);
    }

    @Override
    public List<Note> listeNote(Integer userId, Integer subjectId) {
        User user = userRepository.findById(userId).orElse(null);
        Subject subject = subjectRepo.findById(subjectId).orElse(null);

        if (user == null || subject == null) {
            // Handle the case where the user or subject is not found
            return Collections.emptyList();
        }

        return noteRepo.findAll()
                .stream()
                .filter(note -> note.getUser().equals(user) && note.getSubject().equals(subject))
                .collect(Collectors.toList());
    }









}
