package com.cpp.servicebooking.controllers;

import com.cpp.servicebooking.Request.ServiceRequest.ServiceProvideRequest;
import com.cpp.servicebooking.models.ServiceProvide;
import com.cpp.servicebooking.services.MapValidationErrorService;
import com.cpp.servicebooking.services.ServiceProvideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/provide")
public class ProviderController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    
    @Autowired
    private ServiceProvideService serviceProvideService;

    @PostMapping("/update")
    public ResponseEntity<?> updateService(@Valid @RequestBody ServiceProvideRequest serviceProvideRequest, BindingResult result, Principal principal) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        ServiceProvide serviceProvide = serviceProvideService.updateService(serviceProvideRequest, principal.getName());

        return new ResponseEntity<ServiceProvide>(serviceProvide, HttpStatus.CREATED);
    }


}
