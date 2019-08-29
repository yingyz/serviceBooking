package com.cpp.servicebooking.controllers;


import com.cpp.servicebooking.Request.AdminRequest.RoleRequest;
import com.cpp.servicebooking.Request.AdminRequest.ServiceTypeRequest;
import com.cpp.servicebooking.models.Role;
import com.cpp.servicebooking.models.ServiceType;
import com.cpp.servicebooking.services.MapValidationErrorService;
import com.cpp.servicebooking.services.RoleService;
import com.cpp.servicebooking.services.ServicetypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ServicetypeService saveServicetype;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/role")
    public ResponseEntity<?> saveRole(@Valid @RequestBody RoleRequest roleRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Role role = new Role(roleRequest.getName());
        Role newRole = roleService.saveRole(role);
        return new ResponseEntity<Role>(newRole, HttpStatus.CREATED);
    }

    @PostMapping("/servicetype")
    public ResponseEntity<?> saveServicetype(@Valid @RequestBody ServiceTypeRequest serviceTypeRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        ServiceType serviceType = new ServiceType(serviceTypeRequest.getName());
        ServiceType newserviceType = saveServicetype.saveServicetype(serviceType);
        return new ResponseEntity<ServiceType>(newserviceType, HttpStatus.CREATED);
    }

    @GetMapping("/roles")
    public Iterable<Role> getRoles() {
        return roleService.findAllRoles();
    }
}
