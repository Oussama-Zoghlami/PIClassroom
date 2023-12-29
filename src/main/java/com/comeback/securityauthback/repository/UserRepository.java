package com.comeback.securityauthback.repository;

import com.comeback.securityauthback.entities.Role;
import com.comeback.securityauthback.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRole(@Param("role")Role role);

    Optional<User> findByEmail(String email);


    Optional<User> findById(Integer id);
    List<User> findBySchoolClass_IdClass(Integer classId);



}
