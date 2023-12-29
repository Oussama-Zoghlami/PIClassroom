package com.comeback.securityauthback.controller;

import com.comeback.securityauthback.entities.*;
import com.comeback.securityauthback.services.Services;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @PostMapping("/addsubject")
    public Subject addSubject(@RequestBody Subject subject){
        Subject s = services.addSubject(subject);
        return s;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/affichClass/{class-id}")
    public SchoolClass affichClass(@PathVariable ("class-id")Integer idClass){
        SchoolClass s=services.affichClass(idClass);
        return s;
    }
    @PreAuthorize("permitAll()")
    @DeleteMapping("/deleteClass/{class-id}")
    public void deleteClass(@PathVariable ("class-id")Integer idClass){
        services.deleteClass(idClass);

    }
    @PreAuthorize("permitAll()")
    @PostMapping("/addevent")
    public Event addEvent(@RequestBody Event event){
        Event e = services.addEvent(event);
        return e;
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/affichEvent/{event-id}")
    public Event affichEvent(@PathVariable ("event-id")Long idEvent){
        Event e=services.affichEvent(idEvent);
        return e;
    }
    @PreAuthorize("permitAll()")
    @DeleteMapping("/deleteEvent/{event-id}")
    public void deleteEvent(@PathVariable ("event-id")Long idEvent){
        services.deleteEvent(idEvent);

    }
    @PreAuthorize("permitAll()")
    @PutMapping("/affecterUtilisateurClasse/{idUser}/{idClass}")
    public void affectUtilisateurClasse (@PathVariable Integer idUser,@PathVariable Integer idClass){
        services.affectUtilisateurClass(idUser,idClass);
    }
    @PreAuthorize("permitAll()")
    @PutMapping("/affecterUtilisateurSubject/{idUser}/{idSubject}")
    public void affectUtilisateurSubject (@PathVariable Integer idUser,@PathVariable Integer idSubject){
        services.affectUtilisateurSubject(idUser,idSubject);
    }
    @PreAuthorize("permitAll()")
    @PutMapping("/affecterSubjectClass/{idSubject}/{idClass}")
    public void affectSubjectClass (@PathVariable Integer idSubject,@PathVariable Integer idClass){
        services.affectSubjectClass(idSubject,idClass);
    }
    @PreAuthorize("permitAll()")
    @PutMapping("/updateEvent")
    public Event updateEvent(@RequestBody Event event){
        Event e=services.updateEvent(event);
        return e;
    }
    @PreAuthorize("permitAll()")
    @PutMapping("/updateClass")
    public SchoolClass updateClass(@RequestBody SchoolClass schoolClass){
        SchoolClass s = services.updateClass(schoolClass);
                return s;
    }
    @PreAuthorize("permitAll()")
    @PutMapping("/updateSubject")
    public Subject updateSubject(@RequestBody Subject subject){
        Subject s = services.updateSubject(subject);
        return s;
    }
    @PreAuthorize("permitAll()")
    @PostMapping("/addAbsence/{userId}/{subjectId}")
    public void addAbsenceToUser(@PathVariable Integer userId,@PathVariable Integer subjectId) {
        services.addAbsenceToUser(userId,subjectId);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/displayUser/{userId}")
    public Map<String, Object> displayUser(@PathVariable Integer userId) {
        User user = services.findUserById(userId);
        if (user != null) {
            Map<String, Object> userData = Map.of(
                    "user", user,
                    "subjects", user.getSubjects(),
                    "absences", user.getSubjects().stream()
                            .collect(Collectors.toMap(Subject::getIdSubject, Subject::getAbsence))
            );
            return userData;
        } else {
            throw new EntityNotFoundException("User not found with ID: " + userId);
        }
    }





}
