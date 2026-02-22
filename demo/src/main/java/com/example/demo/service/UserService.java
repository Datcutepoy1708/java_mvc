package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.respository.UserRepository;

@Service
public class UserService {
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private final UserRepository userRepository;
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
}
