package com.cpp.servicebooking.controllers;


import com.cpp.servicebooking.Request.AdminRequest.RoleRequest;
import com.cpp.servicebooking.Request.AdminRequest.ServiceTypeRequest;
import com.cpp.servicebooking.models.Role;
import com.cpp.servicebooking.models.ServiceType;
import com.cpp.servicebooking.models.User;
import com.cpp.servicebooking.models.dto.UserDto;
import com.cpp.servicebooking.services.MapValidationErrorService;
import com.cpp.servicebooking.services.RoleService;
import com.cpp.servicebooking.services.ServicetypeService;
import com.cpp.servicebooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ServicetypeService servicetypeService;

    @Autowired
    private UserService userService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/role")
    public ResponseEntity<?> saveRole(@Valid @RequestBody RoleRequest roleRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Role role = roleService.saveRole(roleRequest);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @PostMapping("/serviceType")
    public ResponseEntity<?> saveServiceType(@Valid @RequestBody ServiceTypeRequest serviceTypeRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        ServiceType serviceType = servicetypeService.saveServicetype(serviceTypeRequest);
        return new ResponseEntity<>(serviceType, HttpStatus.CREATED);
    }

    @GetMapping("/role")
    public ResponseEntity<Iterable<Role>> getRoles() {
        return ResponseEntity.ok(roleService.findAllRoles());
    }

    @GetMapping("/serviceType")
    public ResponseEntity<Iterable<ServiceType>> getServiceTypes() {
        return ResponseEntity.ok(servicetypeService.findAllServiceTypes());
    }

    @GetMapping("/role/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name) { return ResponseEntity.ok(roleService.findRoleByRolename(name));}

    @GetMapping("/serviceType/{name}")
    public ResponseEntity<ServiceType> getServiceTypeByName(@PathVariable String name) {
        return ResponseEntity.ok(servicetypeService.findServiceByServicename(name));
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/user/{name}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.findUserByName(name));
    }
}
