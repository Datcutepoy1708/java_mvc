package com.example.demo.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
     User save(User datcutepoy);
    
}