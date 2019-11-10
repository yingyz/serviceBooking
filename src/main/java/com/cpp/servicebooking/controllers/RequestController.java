package com.cpp.servicebooking.controllers;

import com.cpp.servicebooking.Request.OrderRequestRequest.RequestOrderRequest;
import com.cpp.servicebooking.models.dto.RequestDto;
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

        RequestDto requestDto = requestOrderService.createRequestOrder(requestOrderRequest, principal.getName());

        return new ResponseEntity<>(requestDto, HttpStatus.CREATED);
    }

    @PutMapping("/id/{RequestId}")
    public ResponseEntity<?> updateRequest(@PathVariable String RequestId, @Valid @RequestBody RequestOrderRequest requestOrderRequest, BindingResult result, Principal principal) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        RequestDto requestDto = requestOrderService.updateRequest(RequestId, requestOrderRequest, principal.getName());
        return new ResponseEntity<>(requestDto, HttpStatus.OK);
    }

    @GetMapping("/All")
    public ResponseEntity<List<RequestDto>> getAllRequests(){
        return new ResponseEntity<>(requestOrderService.findAllRequest(), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<List<RequestDto>> getMyRequests(Principal principal){
        return new ResponseEntity<>(requestOrderService.findRequestsByUsername(principal.getName()), HttpStatus.OK);
    }


    @GetMapping("/all/active")
    public ResponseEntity<List<RequestDto>> getAllActiveRequests() {
        return new ResponseEntity<>(requestOrderService.findAllActiveOrInactiveRequest(true), HttpStatus.OK);
    }

    @GetMapping("/all/inactive")
    public ResponseEntity<List<RequestDto>> getAllInactiveRequests() {
        return new ResponseEntity<>(requestOrderService.findAllActiveOrInactiveRequest(false), HttpStatus.OK);
    }

    @GetMapping("/me/active")
    public ResponseEntity<List<RequestDto>> getMyActiveRequests(Principal principal){
        return new ResponseEntity<>(requestOrderService.findRequestsByUsername(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/me/inactive")
    public ResponseEntity<List<RequestDto>> getMyInactiveRequests(Principal principal){
        return new ResponseEntity<>(requestOrderService.findActiveOrInactiveRequestsByUsername(false, principal.getName()), HttpStatus.OK);
    }


    @GetMapping("/list/{RequestId}")
    public ResponseEntity<?> getRequestById(@PathVariable String RequestId) {
        RequestDto requestDto = requestOrderService.findById(RequestId);
        return new ResponseEntity<>(requestDto, HttpStatus.OK);
    }


    @DeleteMapping("/id/{RequestId}")
    public ResponseEntity<?> deleteRequest(@PathVariable String RequestId, Principal principal) {
        requestOrderService.deleteRequest(RequestId, principal.getName());
        return new ResponseEntity<>("Request with ID: '"+RequestId+"' was deleted", HttpStatus.OK);
    }

}
