package com.example.demo.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.domain.dto.RegisterDTO;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class HomePageController {

    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public HomePageController(ProductService productService,UserService userService,PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.userService=userService;
        this.passwordEncoder=passwordEncoder;
    }
    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products=this.productService.getAllProducts();
        model.addAttribute("products",products);
        return "client/homepage/show";
    }
    @GetMapping(value = "/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }
    
    @PostMapping(value="/register")
    public String handleRegister(@ModelAttribute("registerUser") @Valid RegisterDTO registerDTO,
    BindingResult bindingResult
    ){
        List<FieldError> errors=bindingResult.getFieldErrors();
        for(FieldError error:errors){
            System.out.println(">>>>"+error.getField()+" - "+error.getDefaultMessage());
        }
        User user=this.userService.registerDTOtoUser(registerDTO);
        String hashPassword=this.passwordEncoder.encode(registerDTO.getPassword());
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));
        this.userService.handleSaveUser(user);
        return "redirect:/login";
    }

    @GetMapping(value="/login")
    public String getLoginPage(Model model){
        return "client/auth/login";
    } 
    
}
