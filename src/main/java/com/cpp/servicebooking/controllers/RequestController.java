package com.cpp.servicebooking.controllers;

import com.cpp.servicebooking.Request.OrderRequestRequest.RequestOrderRequest;
import com.cpp.servicebooking.exceptions.Exception.RequestOrderNotFoundException;
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
@RequestMapping("/api/request")
public class RequestController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private RequestOrderService requestOrderService;

    @PostMapping()
    public ResponseEntity<?> createRequest(@Valid @RequestBody RequestOrderRequest requestOrderRequest, BindingResult result, Principal principal) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        RequestOrder requestOrder = requestOrderService.createRequestOrder(requestOrderRequest, principal.getName());

        return new ResponseEntity<RequestOrder>(requestOrder, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<RequestOrder> getAllRequests(){
        return requestOrderService.findAllRequest();
    }

    @GetMapping("/me")
    public Iterable<RequestOrder> getMyRequests(Principal principal){
        return requestOrderService.findRequestsByUsername(principal.getName());
    }

    //need to implemented
    @GetMapping("/id/{RequestId}")
    public ResponseEntity<?> getRequestById(@PathVariable String RequestId) {
        RequestOrder requestOrder = requestOrderService.findById(RequestId);
        return new ResponseEntity<RequestOrder>(requestOrder, HttpStatus.OK);
    }

    //need to implemented
    @DeleteMapping("/id/{RequestId}")
    public ResponseEntity<?> deleteRequest(@PathVariable String RequestId, Principal principal) {
        requestOrderService.deleteRequest(RequestId, principal.getName());
        return new ResponseEntity<String>("Request with ID: '"+RequestId+"' was deleted", HttpStatus.OK);
    }
}
