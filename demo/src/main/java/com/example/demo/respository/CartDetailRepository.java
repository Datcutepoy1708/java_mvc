package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Cart;
import com.example.demo.domain.CartDetail;
import com.example.demo.domain.Product;

import jakarta.transaction.Transactional;

public interface CartDetailRepository extends JpaRepository<CartDetail,Long>  {
    boolean existsByCartAndProduct(Cart cart,Product product);
    CartDetail findByCartAndProduct(Cart cart,Product product);

    @Modifying
    @Transactional
    @Query("DELETE FROM CartDetail cd WHERE cd.cart = :cart")
    void deleteByCart(@Param("cart") Cart cart);
}
