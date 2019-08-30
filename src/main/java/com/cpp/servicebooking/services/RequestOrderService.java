package com.cpp.servicebooking.services;

import com.cpp.servicebooking.Request.OrderRequestRequest.RequestOrderRequest;
import com.cpp.servicebooking.models.RequestOrder;
import com.cpp.servicebooking.models.User;
import com.cpp.servicebooking.repository.RequestOrderRepo;
import com.cpp.servicebooking.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestOrderService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RequestOrderRepo requestOrderRepo;

    public RequestOrder createRequestOrder(RequestOrderRequest requestOrderRequest, String name) {
        User user = userRepo.findByUsername(name);
        RequestOrder requestOrder = new RequestOrder();
        requestOrder.setUser(user);
        requestOrder.setInfo(requestOrderRequest.getInfo());
        requestOrder.setTitle(requestOrderRequest.getTitle());
        requestOrder.setActive(true);
        requestOrderRepo.save(requestOrder);
        return requestOrder;
    }

    public Iterable<RequestOrder> findAllRequest(){
        return requestOrderRepo.findAll();
    }
}
