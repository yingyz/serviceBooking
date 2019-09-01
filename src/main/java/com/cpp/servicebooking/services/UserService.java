package com.cpp.servicebooking.services;

import com.cpp.servicebooking.Request.UserRequest.SignUpRequest;
import com.cpp.servicebooking.exceptions.Exception.DatabaseNotFoundException;
import com.cpp.servicebooking.exceptions.Exception.DuplicateAccountException;
import com.cpp.servicebooking.models.Role;
import com.cpp.servicebooking.models.ServiceProvide;
import com.cpp.servicebooking.models.User;
import com.cpp.servicebooking.models.UserInfo;
import com.cpp.servicebooking.repository.RoleRepo;
import com.cpp.servicebooking.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(SignUpRequest signUpRequest) {
        try {
            User user = new User();
            user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
            user.setUsername(signUpRequest.getUsername());
            Role role = roleRepo.findByName(signUpRequest.getRole());

            if (role == null) {
                throw new DatabaseNotFoundException("Role not found in database");
            }
            user.setRole(role);

            UserInfo userInfo = new UserInfo(signUpRequest.getFirstname(), signUpRequest.getLastname(), signUpRequest.getStreetname(), signUpRequest.getCity(),signUpRequest.getState(),Integer.parseInt(signUpRequest.getZipcode()), signUpRequest.getPhone());
            user.setUserInfo(userInfo);

            if (role.getName().equals("Service")) {
                user.setServiceProvide(new ServiceProvide());
            }

            return userRepo.save(user);
        } catch (DatabaseNotFoundException e) {
          throw e;
        } catch(Exception e) {
            throw new DuplicateAccountException("Username '"+ signUpRequest.getUsername() +"' already exists");
        }
    }

    public List<User> findAllUsers() {
        return (ArrayList)userRepo.findAll();
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
