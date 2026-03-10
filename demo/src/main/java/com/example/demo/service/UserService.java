package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.domain.dto.RegisterDTO;
import com.example.demo.respository.OrderRepository;
import com.example.demo.respository.ProductRepository;
import com.example.demo.respository.RoleRepository;
import com.example.demo.respository.UserRepository;

@Service
public class UserService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    public UserService(UserRepository userRepository,RoleRepository roleRepository,OrderRepository orderRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.roleRepository=roleRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public String handleHello() {
        return "Hello from Service";
    }

    public List<User> getAllUsers() {
      return this.userRepository.findAll();
    }

    public List<User> getAllUserByEmail(String email) {
        return this.userRepository.findOneByEmail(email);
    }

    public User handleSaveUser(User user) {
       User datcute=this.userRepository.save(user);
       return datcute;
    }

    public User findUserById(long id) {
        User user= this.userRepository.findById(id);
        return user;
    }

    public void deleteAUser(long id){
        this.userRepository.deleteById(id);
    }
    public Role getRoleByName(String name){
        return this.roleRepository.findByName(name);
    }
    
    public User registerDTOtoUser(RegisterDTO registerDTO) {
           User user=new User();
           user.setFullname((registerDTO.getFirstName()+ " "+registerDTO.getLastName()));
           user.setEmail(registerDTO.getEmail());
           user.setPassword(registerDTO.getEmail());
           return user;
    }

    public boolean checkEmailExist(String email){
        return this.userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public long countOrders(){
        return  this.orderRepository.count();
    }
    public long countProducts(){
        return  this.productRepository.count();
    }
    public long countUsers(){
        return  this.userRepository.count();
    }
}
