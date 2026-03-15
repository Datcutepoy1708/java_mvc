package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.OrderDetail;
import com.example.demo.domain.Orders;
import com.example.demo.respository.OrderDetailRepository;
import com.example.demo.respository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    
    public OrderService(OrderRepository orderRepository,OrderDetailRepository orderDetailRepository){
        this.orderRepository=orderRepository;
        this.orderDetailRepository=orderDetailRepository;
    }

    public List<Orders> fetchAllOrders(){
         return this.orderRepository.findAll();
    }

    public Page<Orders> fetchAllOrders(Pageable pageable){
        return this.orderRepository.findAll(pageable);
    }

    public Optional<Orders> fetchOrdersById(long id){
        return this.orderRepository.findById(id);
    } 

    public void deleteOrderById(long id){
        Optional<Orders> orderOptional=this.fetchOrdersById(id);
       if(orderOptional.isPresent()){
          Orders order=orderOptional.get();
          List<OrderDetail> orderDetails=order.getOrderDetails();
          for(OrderDetail orderDetail: orderDetails){
            this.orderDetailRepository.deleteById(orderDetail.getId());
          }
       }
       this.orderRepository.deleteById(id);
    }

    public void updateOrder(Orders order){
        Optional<Orders> ordeOptional=this.fetchOrdersById(order.getId());
        if(ordeOptional.isPresent()){
            Orders currentOrder=ordeOptional.get();
            currentOrder.setStatus(order.getStatus());
            this.orderRepository.save(currentOrder);
        }
    }
}
