package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

}
