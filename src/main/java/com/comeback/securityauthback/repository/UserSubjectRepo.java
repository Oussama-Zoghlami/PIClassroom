package com.comeback.securityauthback.repository;

import com.comeback.securityauthback.entities.UserSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserSubjectRepo extends JpaRepository<UserSubject,Long> {
}
