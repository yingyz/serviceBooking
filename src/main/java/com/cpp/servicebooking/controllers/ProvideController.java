package com.cpp.servicebooking.controllers;

import com.cpp.servicebooking.Request.ServiceRequest.ServiceProvideRequest;
import com.cpp.servicebooking.Response.ServicesResponse;
import com.cpp.servicebooking.models.dto.ServiceDto;
import com.cpp.servicebooking.services.MapValidationErrorService;
import com.cpp.servicebooking.services.ServiceProvideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/provider")
public class ProvideController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    
    @Autowired
    private ServiceProvideService serviceProvideService;

    @PostMapping("/update")
    public ResponseEntity<?> updateService(@Valid @RequestBody ServiceProvideRequest serviceProvideRequest, BindingResult result, Principal principal) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        ServiceDto serviceProvide = serviceProvideService.updateService(serviceProvideRequest, principal.getName());

        return new ResponseEntity<>(serviceProvide, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<ServicesResponse> getServices(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "limit", defaultValue = "Integer.MAX_VALUE") int limit) {
        return ResponseEntity.ok(serviceProvideService.getServices(page,limit));
    }

    @GetMapping("/name/{serviceName}")
    public ResponseEntity<ServicesResponse> getServicesByName(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "limit", defaultValue = "Integer.MAX_VALUE") int limit,
                                               @PathVariable String serviceName) {
        return ResponseEntity.ok(serviceProvideService.getServicesByName(serviceName, page, limit));
    }

    @GetMapping("/language/{languageName}")
    public ResponseEntity<ServicesResponse> getServiceByLanguage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                 @RequestParam(value = "limit", defaultValue = "Integer.MAX_VALUE") int limit,
                                                                 @PathVariable String languageName) {
        return ResponseEntity.ok(serviceProvideService.getServiceByLanguage(languageName, page, limit));
    }

    @GetMapping("/{serviceName}/{languageName}")
    public ResponseEntity<ServicesResponse> getServiceByNameAndService(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                       @RequestParam(value = "limit", defaultValue = "Integer.MAX_VALUE") int limit,
                                                                       @PathVariable String serviceName, @PathVariable String languageName) {
        return ResponseEntity.ok(serviceProvideService.getServiceByNameAndService(serviceName, languageName, page, limit));
    }

    @GetMapping("/me")
    public ResponseEntity<ServiceDto> getServiceByUserName(Principal principal) {
        return ResponseEntity.ok(serviceProvideService.getServiceByUserName(principal.getName()));
    }

}
