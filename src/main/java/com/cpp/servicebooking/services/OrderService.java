package com.cpp.servicebooking.services;

import com.cpp.servicebooking.models.OrderRequest;
import com.cpp.servicebooking.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private static OrderRepo orderRepo;

    public Iterable<OrderRequest> getAllOrders(){
        return orderRepo.findAll();
    }

    public static void insert(OrderRequest orderRequest)
    {
        orderRepo.save(orderRequest);
    }


}
