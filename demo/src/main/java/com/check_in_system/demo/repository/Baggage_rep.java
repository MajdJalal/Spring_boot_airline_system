package com.check_in_system.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.check_in_system.demo.entities.Tables.Baggage;



@Repository
public interface Baggage_rep extends JpaRepository<Baggage,String> {

}
