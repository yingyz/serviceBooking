package com.cpp.servicebooking.services;

import com.cpp.servicebooking.exceptions.DuplicateAccountException;
import com.cpp.servicebooking.models.Role;
import com.cpp.servicebooking.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role findRoleByRolename(String roleName) {
        Role role = roleRepo.findByName(roleName);
        return role;
    }

    public Role saveRole(Role role) {
        try {
            role.setName(role.getName());
            return roleRepo.save(role);
        } catch (Exception e) {
            throw new DuplicateAccountException("Rolename '"+ role.getName() +"' already exists");
        }
    }

    public Iterable<Role> findAllRoles() {
        return roleRepo.findAll();
    }
}
