package com.check_in_system.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.check_in_system.demo.entities.Tables.VerificationToken;

@Repository
public interface  VerificationUserRep extends JpaRepository<VerificationToken, Long>{

    VerificationToken findByToken(String token);

    void deleteByToken(String token);
    
}
