package com.comeback.securityauthback.repository;

import com.comeback.securityauthback.entities.Role;
import com.comeback.securityauthback.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByRole(Role role);
    Optional<User> findById(Integer id);



}
