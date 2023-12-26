package com.comeback.securityauthback.repository;

import com.comeback.securityauthback.entities.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepo extends JpaRepository<SchoolClass,Integer> {
}
