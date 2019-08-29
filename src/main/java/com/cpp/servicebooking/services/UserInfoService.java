package com.cpp.servicebooking.services;

import com.cpp.servicebooking.Request.UserInfoRequest.UserInfoUpdateRequest;
import com.cpp.servicebooking.models.User;
import com.cpp.servicebooking.models.UserInfo;
import com.cpp.servicebooking.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserRepo userRepo;

    public UserInfo updateUserInfo(UserInfoUpdateRequest userInfoUpdateRequest, String name){

        User user = userRepo.findByUsername(name);
        UserInfo userInfo = user.getUserInfo();
        userInfo.setFirstname(userInfoUpdateRequest.getFirstname());
        userInfo.setLastname(userInfoUpdateRequest.getLastname());
        userInfo.setStreetname(userInfoUpdateRequest.getStreetname());
        userInfo.setCity(userInfoUpdateRequest.getCity());
        userInfo.setState(userInfoUpdateRequest.getState());
        userInfo.setZipcode(userInfoUpdateRequest.getZipcode());
        userInfo.setPhone(userInfoUpdateRequest.getPhone());

        user.setUserInfo(userInfo);
        userRepo.save(user);

        return userInfo;

    }

    public UserInfo getUserInfoByName(String name){
        return userRepo.findByUsername(name).getUserInfo();
    }


}
