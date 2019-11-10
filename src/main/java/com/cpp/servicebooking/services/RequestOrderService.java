package com.cpp.servicebooking.services;

import com.cpp.servicebooking.Request.OrderRequestRequest.RequestOrderRequest;
import com.cpp.servicebooking.exceptions.Exception.RequestOrderNotFoundException;
import com.cpp.servicebooking.models.RequestOrder;
import com.cpp.servicebooking.models.User;
import com.cpp.servicebooking.models.dto.RequestDto;
import com.cpp.servicebooking.repository.RequestOrderRepo;
import com.cpp.servicebooking.repository.UserRepo;
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
    private UserService userService;

    public RequestDto createRequestOrder(RequestOrderRequest requestOrderRequest, String usernName) {
        User user = userRepo.findByUsername(usernName);
        RequestOrder requestOrder = new RequestOrder();
        requestOrder.setUser(user);
        requestOrder.setInfo(requestOrderRequest.getInfo());
        requestOrder.setTitle(requestOrderRequest.getTitle());
        requestOrder.setActive(true);
        requestOrderRepo.save(requestOrder);

        RequestDto requestDto = transferToDto(requestOrder);

        return requestDto;
    }

    public RequestDto updateRequest(String RequestId, RequestOrderRequest requestOrderRequest, String name) {
        RequestOrder requestOrder = findRequestById(RequestId);
        if (!requestOrder.getUser().getUsername().equals(name)) {
            throw new RequestOrderNotFoundException("RequestOrder is not yours!");
        }
        requestOrder.setInfo(requestOrderRequest.getInfo());
        requestOrder.setTitle(requestOrderRequest.getTitle());
        requestOrderRepo.save(requestOrder);

        return transferToDto(requestOrder);
    }

    public List<RequestDto> findAllRequest(){
        List<RequestOrder> requestOrders = (ArrayList)requestOrderRepo.findAll();
        return transferToDtos(requestOrders);
    }

    public List<RequestDto> findAllActiveOrInactiveRequest(boolean active) {
        List<RequestOrder> requestOrders = (ArrayList)requestOrderRepo.findAllByActive(active);
        return transferToDtos(requestOrders);
    }

    public List<RequestDto> findRequestsByUsername(String name){
        User user = userRepo.findByUsername(name);
        List<RequestOrder> requestOrders = user.getRequestOrders();
        return transferToDtos(requestOrders);
    }

    public List<RequestDto> findActiveOrInactiveRequestsByUsername(boolean active, String name) {
        User user = userRepo.findByUsername(name);
        List<RequestOrder> requestOrders = (ArrayList) requestOrderRepo.findAllByActiveAndUser(active, user);
        return transferToDtos(requestOrders);
    }

    public RequestOrder findRequestById(String RequestId) {
        long id = Long.parseLong(RequestId);
        RequestOrder requestOrder = requestOrderRepo.findById(id);
        if (requestOrder == null) {
            throw new RequestOrderNotFoundException("RequestOrder not found!");
        }
        return  requestOrder;
    }

    public RequestDto findById(String requestId) {

        return transferToDto(findRequestById(requestId));
    }

    public void deleteRequest(String RequestId, String name) {
        RequestOrder requestOrder = findRequestById(RequestId);
        if (!requestOrder.getUser().getUsername().equals(name)) {
            throw new RequestOrderNotFoundException("RequestOrder is not yours!");
        }

        requestOrderRepo.delete(requestOrder);
    }

    private List<RequestDto> transferToDtos(List<RequestOrder> requestOrders) {
        List<RequestDto> requestDtos = new ArrayList<>();
        for (RequestOrder requestOrder : requestOrders) {
            requestDtos.add(transferToDto(requestOrder));
        }
        return requestDtos;
    }

    private RequestDto transferToDto(RequestOrder requestOrder) {
        RequestDto requestDto = RequestDto.builder()
                .active(requestOrder.getActive())
                .create_At(requestOrder.getCreate_At())
                .info(requestOrder.getInfo())
                .requestId(requestOrder.getId())
                .servicetype(requestOrder.getTitle())
                .update_At(requestOrder.getUpdate_At())
                .userDto(userService.transferUserDto(requestOrder.getUser()))
                .build();
        return requestDto;
    }
}
