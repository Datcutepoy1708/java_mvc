package com.example.demo.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.User;
import com.example.demo.respository.UserRepository;
import com.example.demo.service.UploadService;
import com.example.demo.service.UserService;

import jakarta.servlet.ServletContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class UserController {

    private UserService userService;
    private final UploadService uploadService;
    private PasswordEncoder passwordEncoder;

    
    public UserController(UserService userService,UploadService uploadService, PasswordEncoder passwordEncoder ) {
        this.userService = userService;
        this.uploadService=uploadService;
        this.passwordEncoder=passwordEncoder;
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

    @RequestMapping("/admin/user/detail/{id}")
    public String getUserDetailPage(Model model,@PathVariable long id) {
       System.out.println("check path id: "+id);
       User user=this.userService.findUserById(id);
       model.addAttribute("user",user);
        return "admin/user/show";
    }

    @RequestMapping(value = "/admin/user/update/{id}" ,method = RequestMethod.GET)
    public String getUpdateUserFormPage(Model model, @PathVariable long id) {
        User user = this.userService.findUserById(id);
        model.addAttribute("currentUser",user);
        return "admin/user/update";
    }

    @GetMapping(value = "/admin/user/create" )
    public String createUserFormPage(Model model, @ModelAttribute("newUser") User datcutepoy) {
        return "admin/user/create";
    }

    @PostMapping(value = "/admin/user/create")
    public String createUserPage(Model model, 
        @ModelAttribute("newUser") User datcutepoy,
        @RequestParam("imageFile") MultipartFile file
    ) {
       String avatar=this.uploadService.handleSaveUploadFile(file, "avatar");
       String hashPassword=this.passwordEncoder.encode(datcutepoy.getPassword());
       datcutepoy.setAvatar(avatar);
       datcutepoy.setPassword(hashPassword);
       datcutepoy.setRole(this.userService.getRoleByName(datcutepoy.getRole().getName()));
       //save
       this.userService.handleSaveUser(datcutepoy);
        return "redirect:/admin/user";
    }

    @PostMapping(value = "/admin/user/update/{id}")
    public String postUpdateUser(Model model, @ModelAttribute("currentUser") User user) {
        User currentUser=this.userService.findUserById(user.getId());
        if(currentUser  != null){
            currentUser.setAddress(user.getAddress());
            currentUser.setFullname(user.getFullname());
            currentUser.setPhone(user.getPhone());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping(value = "/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("id",id);
        User user = this.userService.findUserById(id);
        model.addAttribute("currentUser", user);
        return "admin/user/delete";
    }

     @PostMapping(value = "/admin/user/delete/{id}")
    public String postDeleteUserPage(Model model, @ModelAttribute("currentUser") User user) {

        this.userService.deleteAUser(user.getId());
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
