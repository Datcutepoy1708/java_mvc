package com.example.demo.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Orders;
import com.example.demo.domain.User;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
   Optional<Orders> findById(long id);
   List<Orders> findByUser(User user);
   
}
