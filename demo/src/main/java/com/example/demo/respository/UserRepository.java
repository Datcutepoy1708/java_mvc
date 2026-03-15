package com.example.demo.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
     User save(User datcutepoy);
     List<User> findOneByEmail(String email);
     Page<User> findAll(Pageable page);
     User findById(long id);
     void deleteById(long id);
     boolean existsByEmail(String email);
     User findByEmail(String email);
}