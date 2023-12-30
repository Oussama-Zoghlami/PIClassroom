package com.comeback.securityauthback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subject")
    private Integer idSubject;
    @Column(name = "title_subject")
    private String title;


    @ManyToOne
    SchoolClass schoolClass;
    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.ALL)
    private Set<User> users;
    @OneToMany(mappedBy = "subject")
    private Set<Note> notes;





}
