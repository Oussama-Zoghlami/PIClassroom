package com.comeback.securityauthback.services;

import com.comeback.securityauthback.entities.*;

import java.util.List;

public interface Services {
    public SchoolClass addClass(SchoolClass schoolClass);
    public SchoolClass affichClass(Integer idClass);
    public void deleteClass(Integer idClass);
    List<User> getUsersByRole(Role role);

    void addAbsenceToUser(Integer userId);
    public Subject addSubject(Subject subject);

    public Event addEvent(Event event);
    public Event affichEvent(Long idEvent);
    public void deleteEvent(Long idEvent);
    public void affectUtilisateurClass (Integer idUser, Integer idClass);
    public void affectUtilisateurSubject (Integer idUser, Integer idSubject);
    public void affectSubjectClass (Integer idSubject, Integer idClass);


}
