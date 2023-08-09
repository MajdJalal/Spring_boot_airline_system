package com.check_in_system.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.check_in_system.demo.entities.Tables.Travelers;


@Repository
public interface Travelers_rep extends JpaRepository<Travelers, String> {

}
