package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
     User save(User datcutepoy);
     List<User> findByEmail(String email);
     List<User> findAll();
    
}