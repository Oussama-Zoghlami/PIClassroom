package com.comeback.securityauthback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_class")

public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_class")
    private Integer idClass;
    @Column(name = "nb_max_class")
    private Integer nbMax;
    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToMany(mappedBy = "schoolClass")
    @JsonIgnore
    private Set<User> users = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schoolClass")
    private Set<Subject> subjects;

}




