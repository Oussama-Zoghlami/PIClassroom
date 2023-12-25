package com.comeback.securityauthback.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user_subject")
public class UserSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private Integer absence;


}
