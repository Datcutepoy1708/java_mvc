package com.example.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Product;

import ch.qos.logback.core.model.Model;


@Controller
public class ProductController {
    @GetMapping("/admin/product")    
    public String getDashboard() {
        return "admin/product/show";
    }
    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model , @ModelAttribute("newProduct") Product prodcut) {
        return "admin/product/create";
    }
    
}
