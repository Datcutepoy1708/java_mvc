package com.example.demo.service;

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
    public User handleSaveUser(User user) {
       User datcute=this.userRepository.save(user);
       return datcute;
    }
}
