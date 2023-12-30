package com.comeback.securityauthback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    @Column(name = "id_note")
    private Long idNote;

    @Column(name = "note")
    private Double value;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}