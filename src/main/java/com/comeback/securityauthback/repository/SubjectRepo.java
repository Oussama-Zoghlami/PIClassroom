package com.comeback.securityauthback.repository;

import com.comeback.securityauthback.entities.Subject;
import com.comeback.securityauthback.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepo extends JpaRepository<Subject,Integer> {
    List<Subject> findBySchoolClass_IdClass(Integer classId);
}
