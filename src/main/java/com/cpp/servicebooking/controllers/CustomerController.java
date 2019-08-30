package com.cpp.servicebooking.controllers;

import com.cpp.servicebooking.Request.OrderRequestRequest.RequestOrderRequest;
import com.cpp.servicebooking.models.RequestOrder;
import com.cpp.servicebooking.services.MapValidationErrorService;
import com.cpp.servicebooking.services.RequestOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private RequestOrderService requestOrderService;

    @PostMapping("/requestorder")
    public ResponseEntity<?> createRequest(@Valid @RequestBody RequestOrderRequest requestOrderRequest, BindingResult result, Principal principal) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        RequestOrder requestOrder = requestOrderService.createRequestOrder(requestOrderRequest, principal.getName());

        return new ResponseEntity<RequestOrder>(requestOrder, HttpStatus.CREATED);
    }

    @GetMapping("/allrequests")
    public Iterable<RequestOrder> getAllRequests(){
        return requestOrderService.findAllRequest();
    }

    @GetMapping("/myrequests")
    public Iterable<RequestOrder> getMyRequests(Principal principal){
        return requestOrderService.findRequestsByUsername(principal.getName());

    }
}
