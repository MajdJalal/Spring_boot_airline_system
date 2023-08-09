package com.check_in_system.demo.repository;



import org.springframework.stereotype.Repository;
import com.check_in_system.demo.entities.Tables.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface reg_rep extends JpaRepository<User,Long>{

    User findByPassword(String current);

    User findByEmail(String encode);

}
