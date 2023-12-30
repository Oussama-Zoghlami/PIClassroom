package com.comeback.securityauthback.repository;

import com.comeback.securityauthback.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository <Note, Long> {


}


