package com.cpp.servicebooking.services;

import com.cpp.servicebooking.Request.AdminRequest.RoleRequest;
import com.cpp.servicebooking.exceptions.Exception.DatabaseNotFoundException;
import com.cpp.servicebooking.exceptions.Exception.DuplicateAccountException;
import com.cpp.servicebooking.models.Role;
import com.cpp.servicebooking.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.tools.jstat.Literal;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role findRoleByRolename(String roleName) {
        Role role = roleRepo.findByName(roleName);
        if (role == null) {
            throw new DatabaseNotFoundException("Role not found in database");
        }
        return role;
    }

    public Role saveRole(RoleRequest roleRequest) {
        try {
            Role role = new Role();
            role.setName(roleRequest.getName());
            return roleRepo.save(role);
        } catch (Exception e) {
            throw new DuplicateAccountException("Rolename '"+ roleRequest.getName() +"' already exists");
        }
    }

    public Iterable<Role> findAllRoles() {
        return roleRepo.findAll();
    }
}
