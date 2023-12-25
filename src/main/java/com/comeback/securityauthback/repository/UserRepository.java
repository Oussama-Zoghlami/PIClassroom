package com.comeback.securityauthback.repository;

import com.comeback.securityauthback.entities.Role;
import com.comeback.securityauthback.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findByRole(Role role);

}
