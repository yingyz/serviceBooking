package com.cpp.servicebooking.controllers;

import com.cpp.servicebooking.Request.UserInfoRequest.UserInfoUpdateRequest;
import com.cpp.servicebooking.models.User;
import com.cpp.servicebooking.models.UserInfo;
import com.cpp.servicebooking.services.MapValidationErrorService;
import com.cpp.servicebooking.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/userinfo")
public class UserInfoController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserInfoService userInfoService;

    @PutMapping()
    public ResponseEntity<?> updateUserInfo(@Valid @RequestBody UserInfoUpdateRequest userInfoUpdateRequest, BindingResult result, Principal principal){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        UserInfo userInfo = userInfoService.updateUserInfo(userInfoUpdateRequest, principal.getName());

        return new ResponseEntity<UserInfo>(userInfo, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUserInfo(Principal principal){

        UserInfo userInfo = userInfoService.getUserInfoByName(principal.getName());

        return new ResponseEntity<UserInfo>(userInfo, HttpStatus.OK);

    }
}
