package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.respository.UserRepository;
import com.example.demo.service.UserService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;



@Controller
public class UserController {

    private UserService userService;


    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        String test=this.userService.handleHello();
        List<User> arrUsers=this.userService.getAllUserByEmail("dtcutepoy1708@gmail.com");
        System.out.println(arrUsers);
        model.addAttribute("datcute",test);
        model.addAttribute("datcutepoy","from model");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        model.addAttribute("newUser",new User());
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users1",users);
        return "admin/user/table";
    }

    @RequestMapping(value = "/admin/user/create" ,method = RequestMethod.GET)
    public String createUserFormPage(Model model, @ModelAttribute("newUser") User datcutepoy) {
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create" ,method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User datcutepoy) {
        this.userService.handleSaveUser(datcutepoy);
        return "redirect:/admin/user";
    }
}

// @RestController
// public class UserController {

//     private UserService userService;

//     public UserController(UserService userService) {
//         this.userService = userService;
//     }

//     @GetMapping("/")
//     public String getHomePage() {
//         return this.userService.handleHello();
//     }
// }
