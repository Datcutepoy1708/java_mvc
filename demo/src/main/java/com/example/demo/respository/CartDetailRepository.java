package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.CartDetail;

public interface CartDetailRepository extends JpaRepository<CartDetail,Long>  {
    
}
