package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.example.demo.domain.Cart;
import com.example.demo.domain.CartDetail;
import com.example.demo.domain.OrderDetail;
import com.example.demo.domain.Orders;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.respository.CartDetailRepository;
import com.example.demo.respository.CartRepository;
import com.example.demo.respository.OrderDetailRepository;
import com.example.demo.respository.OrderRepository;
import com.example.demo.respository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {
   private final ProductRepository productRepository;
   private final CartRepository cartRepository;
   private final CartDetailRepository cartDetailRepository;
   private final OrderRepository orderRepository;
   private final OrderDetailRepository orderDetailRepository;
//    private final UserRepository userRepository;
  private final UserService userService;
   public ProductService(ProductRepository productRepository,CartRepository cartRepository,CartDetailRepository cartDetailRepository,UserService userService, OrderRepository orderRepository, OrderDetailRepository orderDetailRepository ){
    this.productRepository=productRepository;
    this.cartDetailRepository=cartDetailRepository;
    this.cartRepository=cartRepository;
    this.userService=userService;
    this.orderRepository = orderRepository;
    this.orderDetailRepository = orderDetailRepository;
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
    public void handleAddProductToCart(String email,Long productId,HttpSession session) {
         User user=this.userService.getUserByEmail(email);
         if(user !=null){
            Cart cart=this.cartRepository.findByUser(user);
            if(cart ==null){
                // tạo mới cart
                Cart newCart=new Cart();
                newCart.setUser(user);
                newCart.setSum(0);
                cart=this.cartRepository.save(newCart);
            }
            // save cart_detail
            // tìm product by id
            Optional<Product> productOptional=this.productRepository.findById(productId);
            if(productOptional.isPresent()){
                Product realProduct=productOptional.get();

                //check sản phẩm
              CartDetail oldDetail=this.cartDetailRepository.findByCartAndProduct(cart, realProduct);
              if(oldDetail ==null){
                 
                CartDetail cartDetail=new CartDetail();
                cartDetail.setCart(cart);
                cartDetail.setProduct(realProduct);
                cartDetail.setPrice(realProduct.getPrice());
                cartDetail.setQuantity(1);
                this.cartDetailRepository.save(cartDetail);

                //update cart (sum)
                int s=cart.getSum()+1;
                cart.setSum(cart.getSum()+1);
                this.cartRepository.save(cart);
                session.setAttribute("sum", s);

              }else{
                oldDetail.setQuantity(oldDetail.getQuantity()+1);
                this.cartDetailRepository.save(oldDetail);
              }
                //check sản phẩm
              
            }
         }
    }

    public Cart fetchByUser(User user){
        return this.cartRepository.findByUser(user);
    }

    public void handleRemoveCartDetail(long cartDetailId,HttpSession session){
      Optional<CartDetail> cartDetailOptional=this.cartDetailRepository.findById(cartDetailId);
      if(cartDetailOptional.isPresent()){
        CartDetail cartDetail=cartDetailOptional.get();

        Cart currentCart=cartDetail.getCart();

        this.cartDetailRepository.deleteById(cartDetailId);

        if(currentCart.getSum() > 1){
          int s= currentCart.getSum() - 1;
          currentCart.setSum(s);
          session.setAttribute("sum", s);
          this.cartRepository.save(currentCart);
        }else{
          currentCart.setSum(0);
          session.setAttribute("sum", 0);
          this.cartRepository.save(currentCart);
        }
      }
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails){
      for(CartDetail cartDetail: cartDetails){
        Optional<CartDetail> cartDetaiOptional=this.cartDetailRepository.findById(cartDetail.getId());
        if(cartDetaiOptional.isPresent()){
          CartDetail currentCartDetail=cartDetaiOptional.get();
          currentCartDetail.setQuantity(cartDetail.getQuantity());
          this.cartDetailRepository.save(currentCartDetail);
        }
      }
    }

    public void handlePlaceOrder(User user, HttpSession session, String receiverName, String receiverAddress, String receiverPhone) {
        // create order
        Orders order=new Orders();
        order.setUser(user);
        order.setReceiverAddress(receiverAddress);
        order.setReceiverName(receiverName);
        order.setReceiverPhone(receiverPhone);

        order=this.orderRepository.save(order);

        //create order detail
        
        //step 1: get cart by user
        Cart cart=this.cartRepository.findByUser(user);
        if(cart != null){
          List<CartDetail> cartDetails= cart.getCartDetails();

          if(cartDetails != null){
            for(CartDetail cartDetail :cartDetails){
              OrderDetail orderDetail=new OrderDetail();
              orderDetail.setOrder(order);
              orderDetail.setProduct(cartDetail.getProduct());
              orderDetail.setPrice(cartDetail.getPrice());
              orderDetail.setQuantity(cartDetail.getQuantity());
              this.orderDetailRepository.save(orderDetail);
            }
          }

          // step 2: bulk delete cart_detail
          this.cartDetailRepository.deleteByCart(cart);
          
          // DO NOT delete the cart to avoid bidirectional relationship issues with User
          // Just reset its sum to 0
          cart.setSum(0);
          this.cartRepository.save(cart);
          
          //step 3: update session (reset số trên icon giỏ hàng về 0)
          session.setAttribute("sum", 0);
        }

    }


}
