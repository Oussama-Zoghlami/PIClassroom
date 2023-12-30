package com.comeback.securityauthback.services;

import com.comeback.securityauthback.entities.*;

import java.util.List;

public interface Services {
    public SchoolClass addClass(SchoolClass schoolClass);
    public SchoolClass affichClass(Integer idClass);
    public void deleteClass(Integer idClass);
    List<User> getUsersByRole(Role role);


    public Subject addSubject(Subject subject);

    public Event addEvent(Event event);
    public Event affichEvent(Long idEvent);
    public void deleteEvent(Long idEvent);
    public void affectUtilisateurClass (Integer idUser, Integer idClass);
    public void affectUtilisateurSubject (Integer idUser, Integer idSubject);
    public void affectSubjectClass (Integer idSubject, Integer idClass);
    public Event updateEvent(Event event);
    public SchoolClass updateClass(SchoolClass schoolClass);
    public Subject updateSubject(Subject subject);


    User findUserById(Integer userId);


    public Subject getSubjectDetailsById(Integer idSubject);
    List<User> getUsersByClassId(Integer classId);
    List<String> getUsersByClassRole(Role role, Integer classId);
    List<Subject> getSubjectByClassId(Integer classId);
    public void addNoteWithParams(Long userId, Integer subjectId, Double noteValue);
    public List<Note> listeNote(Integer userId ,Integer subjectId);



}
