package com.comeback.securityauthback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private Integer idEvent;
    @Column(name = "date_debut_event")
    private LocalDate dateDebut;
    @Column(name = "date_fin_event")
    private LocalDate dateFin;
    @Column(name = "lieu_event")
    private String lieuEvent ;

    @ManyToOne
    User user;



}
