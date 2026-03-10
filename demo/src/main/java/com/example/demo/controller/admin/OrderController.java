package com.example.demo.controller.admin;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

import com.example.demo.domain.Orders;
import com.example.demo.service.OrderService;
import org.springframework.ui.Model;
@Controller
public class OrderController {

    private final OrderService orderService;
    public OrderController (OrderService orderService){
        this.orderService=orderService;
    }

    @GetMapping("/admin/order")    
    public String getDashboard(Model model) {
        List<Orders> orders=this.orderService.fetchAllOrders();
        model.addAttribute("orders",orders);
        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getOrderDetail(Model model, @PathVariable long id) {
        Optional<Orders> order = this.orderService.fetchOrdersById(id);
        if (order.isPresent()) {
            model.addAttribute("order", order.get());
            model.addAttribute("id", id);
            model.addAttribute("orderDetails", order.get().getOrderDetails());
        }
        return "admin/order/detail";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdateOrderPage(Model model, @PathVariable long id) {
        Optional<Orders> order = this.orderService.fetchOrdersById(id);
        if (order.isPresent()) {
            model.addAttribute("order", order.get());
        }
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String postUpdateOrder(@ModelAttribute("order") Orders order) {
        this.orderService.updateOrder(order);
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String getDeleteOrderPage(Model model, @PathVariable long id) {
        Optional<Orders> order = this.orderService.fetchOrdersById(id);
        if (order.isPresent()) {
            model.addAttribute("order", order.get());
        }
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete/{id}")
    public String postDeleteOrder(@PathVariable long id) {
        this.orderService.deleteOrderById(id);
        return "redirect:/admin/order";
    }
}
