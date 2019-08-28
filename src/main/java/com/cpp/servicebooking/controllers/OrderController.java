package com.cpp.servicebooking.controllers;

import com.cpp.servicebooking.models.OrderRequest;
import com.cpp.servicebooking.repository.OrderRepo;
import com.cpp.servicebooking.services.OrderService;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class OrderController {

    private OrderService orderService;

    @GetMapping("/orders")
    public Iterable<OrderRequest> getOrders(){

        return orderService.getAllOrders();
    }

    @PostMapping("/post")
    public void postOrder(OrderRequest orderRequest)
    {
        OrderService.insert(orderRequest);
    }



 /*   @PostMapping("/role")
    public ResponseEntity<?> saveRole(@Valid @RequestBody RoleRequest roleRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Role role = new Role(roleRequest.getName());
        Role newRole = roleService.saveRole(role);
        return new ResponseEntity<Role>(newRole, HttpStatus.CREATED);
    }


  */
}
