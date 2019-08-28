package com.cpp.servicebooking.controllers;

import com.cpp.servicebooking.models.OrderRequest;
import com.cpp.servicebooking.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public Iterable<OrderRequest> getOrders(){

        return orderService.getAllOrders();
    }

    @PostMapping("/post")
    public void postOrder(OrderRequest orderRequest)
    {
        orderService.insert(orderRequest);
    }

}
