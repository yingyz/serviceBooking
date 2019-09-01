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
import java.util.List;

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
    public List<RequestOrder> getAllRequests(Principal principal){
        return requestOrderService.findAllRequest(principal.getName());
    }

    @GetMapping("/active")
    public List<RequestOrder> getAllActiveRequests(Principal principal) {return requestOrderService.findAllActiveOrInactiveRequest(true, principal.getName());}

    @GetMapping("/inactive")
    public List<RequestOrder> getAllInactiveRequests(Principal principal) {return requestOrderService.findAllActiveOrInactiveRequest(false, principal.getName());}


    @GetMapping("/mine")
    public List<RequestOrder> getMyRequests(Principal principal){
        return requestOrderService.findRequestsByUsername(principal.getName());
    }

    @GetMapping("/myactive")
    public List<RequestOrder> getMyActiveRequests(Principal principal){
        return requestOrderService.findActiveOrInactiveRequestsByUsername(true, principal.getName());
    }

    @GetMapping("/myinactive")
    public List<RequestOrder> getMyInactiveRequests(Principal principal){
        return requestOrderService.findActiveOrInactiveRequestsByUsername(false, principal.getName());
    }


    @GetMapping("/id/{RequestId}")
    public ResponseEntity<?> getRequestById(@PathVariable String RequestId) {
        RequestOrder requestOrder = requestOrderService.findById(RequestId);
        return new ResponseEntity<RequestOrder>(requestOrder, HttpStatus.OK);
    }


    @DeleteMapping("/id/{RequestId}")
    public ResponseEntity<?> deleteRequest(@PathVariable String RequestId, Principal principal) {
        requestOrderService.deleteRequest(RequestId, principal.getName());
        return new ResponseEntity<String>("Request with ID: '"+RequestId+"' was deleted", HttpStatus.OK);
    }

    @PutMapping("/id/{RequestId}")
    public ResponseEntity<?> updateRequest(@PathVariable String RequestId, Principal principal) {
        RequestOrder requestOrder = requestOrderService.updateRequest(RequestId, principal.getName());
        return new ResponseEntity<RequestOrder>(requestOrder, HttpStatus.OK);
    }

}
