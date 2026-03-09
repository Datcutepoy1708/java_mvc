package com.example.demo.controller.client;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.Cart;
import com.example.demo.domain.CartDetail;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




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
    @PostMapping(value="/add-product-to-cart/{id}")
    public String addPoroductToCart(@PathVariable long id,HttpServletRequest request){
        HttpSession session=request.getSession(false);
        long productId=id;
         String email=(String)session.getAttribute("email");
        this.productService.handleAddProductToCart(email,productId,session);

        return "redirect:/";
    }

   @GetMapping(value = "/cart/show")
public String getCartPage(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession(false);

    // Kiểm tra session null
    if (session == null || session.getAttribute("id") == null) {
        return "redirect:/login";
    }

    User currentUser = new User();
    long id = (long) session.getAttribute("id");
    currentUser.setId(id);

    Cart cart = this.productService.fetchByUser(currentUser);

    // ✅ Kiểm tra cart null
    if (cart == null) {
        model.addAttribute("cartDetails", new ArrayList<>());
        model.addAttribute("totalPrice", 0.0);
        return "client/cart/show";
    }

    List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>(): cart.getCartDetails();

    double totalPrice = 0;
    for (CartDetail cartDetail : cartDetails) {
        totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();
    }

    model.addAttribute("cartDetails", cartDetails);
    model.addAttribute("totalPrice", totalPrice);
    return "client/cart/show";
}

@PostMapping(value="/delete-cart-product/{id}")
public String deleteCartDetail(@PathVariable long id,HttpServletRequest request){
    HttpSession session=request.getSession(false);
    long cartDetailId=id;
    this.productService.handleRemoveCartDetail(cartDetailId, session);
    
    return "redirect:/cart/show";
}    
}
