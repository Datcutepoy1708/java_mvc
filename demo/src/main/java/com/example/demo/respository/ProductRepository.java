package com.example.demo.respository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
     Product save(Product datcutepoy);
     List<Product> findAll();
     Product findById(long id);
     Page<Product> findAll(Pageable page);
     void deleteById(long id);
}
