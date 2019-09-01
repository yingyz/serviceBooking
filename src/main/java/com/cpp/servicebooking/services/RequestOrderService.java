package com.cpp.servicebooking.services;

import com.cpp.servicebooking.Request.OrderRequestRequest.RequestOrderRequest;
import com.cpp.servicebooking.exceptions.Exception.RequestOrderNotFoundException;
import com.cpp.servicebooking.models.RequestOrder;
import com.cpp.servicebooking.models.User;
import com.cpp.servicebooking.repository.RequestOrderRepo;
import com.cpp.servicebooking.repository.UserRepo;
import com.cpp.servicebooking.util.DistanceCalculator;
import com.cpp.servicebooking.util.RequestOrderComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RequestOrderService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RequestOrderRepo requestOrderRepo;

    @Autowired
    private DistanceCalculator distanceCalculator;

    @Autowired
    private RequestOrderComparator requestOrderComparator;

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

    private List<RequestOrder> sortedList(int zip, List<RequestOrder> ans) {
        for (RequestOrder requestOrder : ans) {
            double dis = distanceCalculator.getDistance(zip, requestOrder.getUser().getUserInfo().getZipcode());
            requestOrder.getUser().getUserInfo().setDistance(dis);
        }
        Collections.sort(ans, requestOrderComparator);
        return ans;
    }

    public List<RequestOrder> findAllRequest(String name){
        User user = userRepo.findByUsername(name);
        int zip = user.getUserInfo().getZipcode();
        List<RequestOrder> ans = sortedList(zip, (ArrayList)requestOrderRepo.findAll());
        return ans;
    }

    public List<RequestOrder> findAllActiveOrInactiveRequest(boolean active, String name) {
        User user = userRepo.findByUsername(name);
        int zip = user.getUserInfo().getZipcode();
        List<RequestOrder> ans = sortedList(zip, (ArrayList)requestOrderRepo.findAllByActive(active));
        return ans;
    }

    public List<RequestOrder> findRequestsByUsername(String name){
        User user = userRepo.findByUsername(name);
        int zip = user.getUserInfo().getZipcode();
        List<RequestOrder> ans = sortedList(zip, user.getRequestOrders());
        return ans;
    }

    public List<RequestOrder> findActiveOrInactiveRequestsByUsername(boolean active, String name) {
        User user = userRepo.findByUsername(name);
        int zip = user.getUserInfo().getZipcode();
        List<RequestOrder> ans = sortedList(zip, (ArrayList) requestOrderRepo.findAllByActiveAndUser(active, user));
        return ans;
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
