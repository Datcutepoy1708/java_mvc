package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.respository.ProductRepository;

@Service
public class ProductService {
   private final ProductRepository productRepository;
   public ProductService(ProductRepository productRepository){
    this.productRepository=productRepository;
   }
   public List<Product> getAllProducts() {
      return this.productRepository.findAll();
    }

   public Product handleSaveProduct(Product product) {
       Product datcute=this.productRepository.save(product);
       return datcute;
    }
    public void deleteAProduct(long id){
        this.productRepository.deleteById(id);
    }
    public Product findProductById(long id) {
        Product product= this.productRepository.findById(id);
        return product;
    }
}
