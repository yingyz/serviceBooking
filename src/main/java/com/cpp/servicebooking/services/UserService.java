package com.cpp.servicebooking.services;

import com.cpp.servicebooking.Request.UserInfoRequest.UserInfoUpdateRequest;
import com.cpp.servicebooking.Request.UserRequest.SignUpRequest;
import com.cpp.servicebooking.exceptions.Exception.DatabaseNotFoundException;
import com.cpp.servicebooking.exceptions.Exception.DuplicateAccountException;
import com.cpp.servicebooking.models.*;
import com.cpp.servicebooking.models.dto.UserDto;
import com.cpp.servicebooking.repository.LanguageRepo;
import com.cpp.servicebooking.repository.RequestOrderRepo;
import com.cpp.servicebooking.repository.RoleRepo;
import com.cpp.servicebooking.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private RequestOrderRepo requestOrderRepo;

    @Autowired
    private LanguageRepo languageRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public void saveUser(SignUpRequest signUpRequest) {
        try {
            User user = new User();
            user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
            user.setUsername(signUpRequest.getUsername());

            Role role = roleRepo.findByName(signUpRequest.getRole());
            if (role == null) {
                throw new DatabaseNotFoundException("Role not found in database");
            }
            user.setRole(role);

            Language language = languageRepo.findByName(signUpRequest.getLanguage());

            if (language == null) {
                throw new DatabaseNotFoundException("Role not found in database");
            }

            UserInfo userInfo = UserInfo.builder()
                    .firstname(signUpRequest.getFirstname())
                    .lastname(signUpRequest.getLastname())
                    .streetname(signUpRequest.getStreetname())
                    .city(signUpRequest.getCity())
                    .state(signUpRequest.getState())
                    .zipcode(Integer.parseInt(signUpRequest.getZipcode()))
                    .phone(signUpRequest.getPhone())
                    .language(language)
                    .build();

            user.setUserInfo(userInfo);
            userRepo.save(user);
        } catch (DatabaseNotFoundException e) {
          throw e;
        } catch(Exception e) {
            throw new DuplicateAccountException("Username '"+ signUpRequest.getUsername() +"' already exists");
        }
    }

    public UserDto updateUserInfo(UserInfoUpdateRequest userInfoUpdateRequest, String name){
        User user = userRepo.findByUsername(name);
        UserInfo userInfo = user.getUserInfo();

        userInfo.setFirstname(userInfoUpdateRequest.getFirstname());
        userInfo.setLastname(userInfoUpdateRequest.getLastname());
        userInfo.setStreetname(userInfoUpdateRequest.getStreetname());
        userInfo.setCity(userInfoUpdateRequest.getCity());
        userInfo.setState(userInfoUpdateRequest.getState());
        userInfo.setZipcode(Integer.parseInt(userInfoUpdateRequest.getZipcode()));
        userInfo.setPhone(userInfoUpdateRequest.getPhone());

        Language language = languageRepo.findByName(userInfoUpdateRequest.getLanguage());

        if (language == null) {
            throw new DatabaseNotFoundException("Role not found in database");
        }

        //update request language
        if (!user.getUserInfo().getLanguage().getName().equals(language.getName())) {
            List<RequestOrder> requestOrders = user.getRequestOrders();

            for (RequestOrder requestOrder : requestOrders) {
                requestOrder.setLanguage(language);
                System.out.println(requestOrder.getLanguage().getName());
            }
        }

        userInfo.setLanguage(language);

        user.setUserInfo(userInfo);
        userRepo.save(user);

        UserDto userDto = transferUserDto(user);

        return userDto;
    }

    public List<UserDto> findAllUsers() {
        List<User> users = (ArrayList) userRepo.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = transferUserDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    public UserDto findUserByName(String name) {
        User user = userRepo.findByUsername(name);
        if (user == null) {
            throw new DatabaseNotFoundException("User " + name + " not found!");
        }

        UserDto userDto = transferUserDto(user);
        return userDto;
    }

    public UserDto transferUserDto(User user) {
        UserInfo userInfo = user.getUserInfo();

        UserDto userDto = UserDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .firstname(userInfo.getFirstname())
                .lastname(userInfo.getLastname())
                .streetname(userInfo.getStreetname())
                .city(userInfo.getCity())
                .state(userInfo.getState())
                .zipcode(userInfo.getZipcode())
                .phone(userInfo.getPhone())
                .language(userInfo.getLanguage().getName())
                .role(user.getRole().getName())
                .build();

        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user==null) new UsernameNotFoundException("User not found");
        return user;
    }

    @Transactional
    public User loadUserById(Long id) {
        User user = userRepo.getById(id);
        if(user==null) new UsernameNotFoundException("User not found");
        return user;
    }
}
