package com.example.demo.controller.client;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.domain.dto.RegisterDTO;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;




@Controller
public class HomePageController {

    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final OrderService orderService;

    public HomePageController(ProductService productService,UserService userService,PasswordEncoder passwordEncoder,OrderService orderService) {
        this.productService = productService;
        this.userService=userService;
        this.passwordEncoder=passwordEncoder;
        this.orderService=orderService;
    }
    @GetMapping("/")
    public String getHomePage(Model model) {
        Pageable pageable = PageRequest.of(0, 8);
        Page<Product> prs = this.productService.getAllProducts(pageable);
        List<Product> products = prs.getContent();
        model.addAttribute("products", products);
        return "client/homepage/show";
    }

    @GetMapping("/products")
    public String getProductListPage(
            Model model,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "sort", defaultValue = "") String sort,
            @RequestParam(value = "factory", required = false) List<String> factories,
            @RequestParam(value = "target", required = false) List<String> targets,
            @RequestParam(value = "price", required = false) List<String> priceRanges) {

        int pageSize = 9;
        if (page < 1) page = 1;

        // Determine sort direction
        Sort sortSpec;
        if ("price-asc".equals(sort)) {
            sortSpec = Sort.by(Sort.Direction.ASC, "price");
        } else if ("price-desc".equals(sort)) {
            sortSpec = Sort.by(Sort.Direction.DESC, "price");
        } else {
            sortSpec = Sort.by(Sort.Direction.DESC, "id");
        }

        Pageable pageable = PageRequest.of(page - 1, pageSize, sortSpec);
        Page<Product> productPage = this.productService.getFilteredProducts(
                pageable, factories, targets, priceRanges);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("totalItems", productPage.getTotalElements());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sort", sort);
        model.addAttribute("factories", factories);
        model.addAttribute("targets", targets);
        model.addAttribute("priceRanges", priceRanges);
        // Pass as joined strings for pagination links
        model.addAttribute("factoryParam",
                factories != null ? String.join("&factory=", factories) : "");
        model.addAttribute("targetParam",
                targets != null ? String.join("&target=", targets) : "");
        model.addAttribute("priceParam",
                priceRanges != null ? String.join("&price=", priceRanges) : "");

        return "client/product/list";
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
        //validate
        if(bindingResult.hasErrors()){
            return "client/auth/register";
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
    @GetMapping(value = "/access-deny")
    public String getDenyPage(Model model){
        return "client/auth/deny";
    }


}
