package com.example.demo.controller.admin;

import java.util.List;

import org.eclipse.tags.shaded.java_cup.runtime.lr_parser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.UploadService;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService,UploadService uploadService){
        this.productService=productService;
        this.uploadService=uploadService;
    }

    @GetMapping(value="/admin/product")    
    public String getProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        List<Product> products=this.productService.getAllProducts();
        model.addAttribute("products",products);
        return "admin/product/show";
    }
    @GetMapping(value="/admin/product/create")
    public String getCreateProductPage(Model model , @ModelAttribute("newProduct") Product prodcut) {
        return "admin/product/create";
    }
    @PostMapping(value = "/admin/product/create")
    public String createProductPage(Model model, 
        @ModelAttribute("newProduct") @Valid Product product,
    BindingResult newProductBindingResult,
    @RequestParam("imageFile") MultipartFile file){
        List<FieldError> errors=newProductBindingResult.getFieldErrors();
       for(FieldError error: errors){
        System.out.println(">>>>"+error.getField()+" - "+error.getDefaultMessage());
       }
       // validate
         if(newProductBindingResult.hasErrors()){
            return "/admin/product/create";
         }
         String image=this.uploadService.handleSaveUploadFile(file, "image");
         product.setImage(image);
         this.productService.handleSaveProduct(product);
         return "redirect:/admin/product";
    }
    @GetMapping(value = "/admin/product/detail/{id}")
    public String getDetailProductPage(Model model,@PathVariable long id){
          Product product=this.productService.findProductById(id);
          model.addAttribute("product",product);
          return "admin/product/detail";
    }
    @GetMapping(value = "/admin/product/update/{id}")
    public String getUpdateProductPage(Model model,@PathVariable long id){
        Product product=this.productService.findProductById(id);
        model.addAttribute("currentProduct",product);
        return "admin/product/update";
    }
    @PostMapping(value="/admin/product/update/{id}")
    public String postUpdateProductPage(Model model,
        @ModelAttribute("currentProduct") Product product, 
        @RequestParam("imageFile") MultipartFile file){
            Product currentProduct =this.productService.findProductById(product.getId());
            if(currentProduct !=null){
                currentProduct.setName(product.getName());
                currentProduct.setPrice(product.getPrice());
                currentProduct.setDetailDesc(product.getDetailDesc());
                currentProduct.setShortDesc(product.getShortDesc());
                currentProduct.setQuantity(product.getQuantity());
                currentProduct.setFactory(product.getFactory());
                currentProduct.setTarget(product.getTarget());
                String image=this.uploadService.handleSaveUploadFile(file, "image");
                currentProduct.setImage(image);
                this.productService.handleSaveProduct(currentProduct);
            }
             return "redirect:/admin/product";
        }
    @GetMapping(value = "/admin/product/delete/{id}")
    public String getDeleteProduct(Model model, @PathVariable long id){
        model.addAttribute("id",id);
        Product product=this.productService.findProductById(id);
        model.addAttribute("currentProduct",product);
        return "admin/product/delete";
    }
    @PostMapping(value = "/admin/product/delete/{id}")
    public String postDeleteProduct(Model model, @ModelAttribute("currentProduct") Product product){
        this.productService.deleteAProduct(product.getId());
        return "redirect:/admin/product";
    }   
}
