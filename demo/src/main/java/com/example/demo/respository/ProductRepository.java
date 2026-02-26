package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
     Product save(Product datcutepoy);
     List<Product> findAll();
     Product findById(long id);
     void deleteById(long id);
}
