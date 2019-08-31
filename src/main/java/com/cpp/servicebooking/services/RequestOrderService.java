package com.cpp.servicebooking.services;

import com.cpp.servicebooking.Request.OrderRequestRequest.RequestOrderRequest;
import com.cpp.servicebooking.exceptions.Exception.RequestOrderNotFoundException;
import com.cpp.servicebooking.models.RequestOrder;
import com.cpp.servicebooking.models.User;
import com.cpp.servicebooking.repository.RequestOrderRepo;
import com.cpp.servicebooking.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public Iterable<RequestOrder> findAllActiveOrInactiveRequest(boolean active) {
        return requestOrderRepo.findAllByActive(active);
    }

    public Iterable<RequestOrder> findRequestsByUsername(String name){
        User user = userRepo.findByUsername(name);
        return user.getRequestOrders();
    }

    public Iterable<RequestOrder> findActiveOrInactiveRequestsByUsername(boolean active, String name) {
        User user = userRepo.findByUsername(name);
        return requestOrderRepo.findAllByActiveAndUser(active, user);
    }

    public RequestOrder findById(String RequestId) {
        long id = Long.parseLong(RequestId);
        RequestOrder requestOrder = requestOrderRepo.findById(id);
        if (requestOrder == null) {
            throw new RequestOrderNotFoundException("RequestOrder not found!");
        }
        return requestOrder;
    }

    public void deleteRequest(String RequestId, String name) {
        RequestOrder requestOrder = findById(RequestId);
        if (!requestOrder.getUser().getUsername().equals(name)) {
            throw new RequestOrderNotFoundException("RequestOrder is not yours!");
        }

        requestOrderRepo.delete(requestOrder);
    }

    public RequestOrder updateRequest(String RequestId, String name) {
        RequestOrder requestOrder = findById(RequestId);
        if (!requestOrder.getUser().getUsername().equals(name)) {
            throw new RequestOrderNotFoundException("RequestOrder is not yours!");
        }
        requestOrder.setActive(false);
        return requestOrderRepo.save(requestOrder);
    }
}
