package com.cpp.servicebooking.controllers;

import com.cpp.servicebooking.Response.JWTLoginSucessReponse;
import com.cpp.servicebooking.Request.UserRequest.LoginRequest;
import com.cpp.servicebooking.Request.UserRequest.SignUpRequest;
import com.cpp.servicebooking.models.*;
import com.cpp.servicebooking.models.dto.TextResponse;
import com.cpp.servicebooking.models.dto.UserDto;
import com.cpp.servicebooking.security.JwtTokenProvider;
import com.cpp.servicebooking.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


import java.util.List;

import static com.cpp.servicebooking.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ServicetypeService servicetypeService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX +  tokenProvider.generateToken(authentication);

        UserDto userDto = userService.findUserByName(loginRequest.getUsername());
        return ResponseEntity.ok(new JWTLoginSucessReponse(jwt, userDto));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;

        userService.saveUser(signUpRequest);
        return new ResponseEntity<>(new TextResponse("User Registered!"), HttpStatus.CREATED);
    }

    @GetMapping("/role")
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> ans = roleService.findAllRoles();
        for (Role r : ans) {
            if (r.getName().equals("Admin")) {
                ans.remove(r);
                break;
            }
        }
        return ResponseEntity.ok(ans);
    }

    @GetMapping("/serviceType")
    public ResponseEntity<Iterable<ServiceType>> getServiceTypes() {
        return ResponseEntity.ok(servicetypeService.findAllServiceTypes());
    }

    @GetMapping("/language")
    public ResponseEntity<Iterable<Language>> getLanguages() {
        return ResponseEntity.ok(languageService.getLanguages());
    }
}
