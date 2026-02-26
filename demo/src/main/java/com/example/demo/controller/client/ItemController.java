package com.example.demo.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;


@Controller
public class ItemController {
    private final ProductService productService;
    public ItemController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/product/{id}")
    public String getProductPage(Model model, @PathVariable long id){
        Product product=this.productService.findProductById(id);
        model.addAttribute("product",product);
        model.addAttribute("id",id);
        return "/client/product/detail";
    }
}
