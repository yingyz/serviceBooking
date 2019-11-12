package com.cpp.servicebooking.services;

import com.cpp.servicebooking.Request.OrderRequestRequest.RequestOrderRequest;
import com.cpp.servicebooking.Response.RequestsResponse;
import com.cpp.servicebooking.exceptions.Exception.DatabaseNotFoundException;
import com.cpp.servicebooking.exceptions.Exception.RequestOrderNotFoundException;
import com.cpp.servicebooking.models.Language;
import com.cpp.servicebooking.models.RequestOrder;
import com.cpp.servicebooking.models.ServiceType;
import com.cpp.servicebooking.models.User;
import com.cpp.servicebooking.models.dto.RequestDto;
import com.cpp.servicebooking.repository.LanguageRepo;
import com.cpp.servicebooking.repository.RequestOrderRepo;
import com.cpp.servicebooking.repository.ServiceTypeRepo;
import com.cpp.servicebooking.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RequestOrderService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RequestOrderRepo requestOrderRepo;

    @Autowired
    private ServiceTypeRepo serviceTypeRepo;

    @Autowired
    private LanguageRepo languageRepo;

    @Autowired
    private UserService userService;

    public RequestDto createRequestOrder(RequestOrderRequest requestOrderRequest, String usernName) {
        ServiceType serviceType = serviceTypeRepo.findByName(requestOrderRequest.getServicetype());
        if (serviceType == null) {
            throw new DatabaseNotFoundException("Service not found in database");
        }
        User user = userRepo.findByUsername(usernName);
        RequestOrder requestOrder = new RequestOrder();
        requestOrder.setUser(user);
        requestOrder.setLanguage(user.getUserInfo().getLanguage());
        requestOrder.setInfo(requestOrderRequest.getInfo());
        requestOrder.setServiceType(serviceType);
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

        ServiceType serviceType = serviceTypeRepo.findByName(requestOrderRequest.getServicetype());
        if (serviceType == null) {
            throw new DatabaseNotFoundException("Service not found in database");
        }

        requestOrder.setInfo(requestOrderRequest.getInfo());
        requestOrder.setServiceType(serviceType);
        requestOrderRepo.save(requestOrder);

        return transferToDto(requestOrder);
    }

    //All Request
    public RequestsResponse findAllRequest(int page, int limit){
        List<RequestOrder> allRequestOrders = (ArrayList)requestOrderRepo.findAll();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<RequestOrder> page1 = requestOrderRepo.findAll(pageableRequest);
        List<RequestOrder> requestOrders = page1.getContent();
        return new RequestsResponse(transferToDtos(requestOrders), allRequestOrders.size());
    }

    //Customer request
    public RequestsResponse findRequestsByUsername(String name, int page, int limit){
        User user = userRepo.findByUsername(name);
        List<RequestOrder> allRequestOrders = user.getRequestOrders();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<RequestOrder> page1 = requestOrderRepo.findAllByUser(user, pageableRequest);
        List<RequestOrder> requestOrders = page1.getContent();
        return new RequestsResponse(transferToDtos(requestOrders), allRequestOrders.size());
    }

    //By Service Type
    public RequestsResponse findRequestByServiceType(String serviceTypeName, int page, int limit) {
        ServiceType serviceType = serviceTypeRepo.findByName(serviceTypeName);
        if (serviceType == null) {
            throw new DatabaseNotFoundException("Service not found in database");
        }
        List<RequestOrder> allRequestOrders = requestOrderRepo.findAllByServiceType(serviceType);
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<RequestOrder> page1 = requestOrderRepo.findAllByServiceType(serviceType, pageableRequest);
        List<RequestOrder> requestOrders = page1.getContent();
        return new RequestsResponse(transferToDtos(requestOrders), allRequestOrders.size());
    }

    //language
    public RequestsResponse findRequestByLanguage(String languageName, int page, int limit) {
        Language language = languageRepo.findByName(languageName);
        if (language == null) {
            throw new DatabaseNotFoundException("Service not found in database");
        }
        List<RequestOrder> allRequestOrders = requestOrderRepo.findAllByLanguage(language);
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<RequestOrder> page1 = requestOrderRepo.findAllByLanguage(language, pageableRequest);
        List<RequestOrder> requestOrders = page1.getContent();
        return new RequestsResponse(transferToDtos(requestOrders), allRequestOrders.size());
    }

    //service and language
    public RequestsResponse findRequestByServiceTypeAndLanguage(String serviceTypeName, String languageName, int page, int limit) {
        ServiceType serviceType = serviceTypeRepo.findByName(serviceTypeName);
        if (serviceType == null) {
            throw new DatabaseNotFoundException("Service not found in database");
        }
        Language language = languageRepo.findByName(languageName);
        if (language == null) {
            throw new DatabaseNotFoundException("Service not found in database");
        }
        List<RequestOrder> allRequestOrders = requestOrderRepo.findAllByServiceTypeAndLanguage(serviceType, language);
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<RequestOrder> page1 = requestOrderRepo.findAllByServiceTypeAndLanguage(serviceType, language, pageableRequest);
        List<RequestOrder> requestOrders = page1.getContent();
        return new RequestsResponse(transferToDtos(requestOrders), allRequestOrders.size());
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
    /*
    public List<RequestDto> findAllActiveOrInactiveRequest(boolean active) {
        List<RequestOrder> requestOrders = (ArrayList)requestOrderRepo.findAllByActive(active);
        return transferToDtos(requestOrders);
    }

    public List<RequestDto> findActiveOrInactiveRequestsByUsername(boolean active, String name) {
        User user = userRepo.findByUsername(name);
        List<RequestOrder> requestOrders = (ArrayList) requestOrderRepo.findAllByActiveAndUser(active, user);
        return transferToDtos(requestOrders);
    }
    */

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
                .servicetype(requestOrder.getServiceType().getName())
                .update_At(requestOrder.getUpdate_At())
                .userDto(userService.transferUserDto(requestOrder.getUser()))
                .build();
        return requestDto;
    }
}
