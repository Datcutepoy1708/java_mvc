package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.respository.RoleRepository;
import com.example.demo.respository.UserRepository;

@Service
public class UserService {
    public UserService(UserRepository userRepository,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository=roleRepository;
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
        return this.userRepository.findByEmail(email);
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
}
