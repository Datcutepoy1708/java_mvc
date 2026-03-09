package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Cart;
import com.example.demo.domain.CartDetail;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.respository.CartDetailRepository;
import com.example.demo.respository.CartRepository;
import com.example.demo.respository.ProductRepository;
import com.example.demo.respository.UserRepository;

@Service
public class ProductService {
   private final ProductRepository productRepository;
   private final CartRepository cartRepository;
   private final CartDetailRepository cartDetailRepository;
//    private final UserRepository userRepository;
  private final UserService userService;
   public ProductService(ProductRepository productRepository,CartRepository cartRepository,CartDetailRepository cartDetailRepository,UserService userService ){
    this.productRepository=productRepository;
    this.cartDetailRepository=cartDetailRepository;
    this.cartRepository=cartRepository;
    this.userService=userService;
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
    public void handleAddProductToCart(String email,Long productId) {
         User user=this.userService.getUserByEmail(email);
         if(user !=null){
            Cart cart=this.cartRepository.findByUser(user);
            if(cart ==null){
                // tạo mới cart
                Cart newCart=new Cart();
                newCart.setUser(user);
                newCart.setSum(1);
                cart=this.cartRepository.save(newCart);
            }
            // save cart_detail
            // tìm product by id
            Optional<Product> productOptional=this.productRepository.findById(productId);
            if(productOptional.isPresent()){
                Product realProduct=productOptional.get();
                CartDetail cartDetail=new CartDetail();
                cartDetail.setCart(cart);
                cartDetail.setProduct(realProduct);
                cartDetail.setPrice(realProduct.getPrice());
                cartDetail.setQuantity(1);

                this.cartDetailRepository.save(cartDetail);
            }
         }
    }
}
