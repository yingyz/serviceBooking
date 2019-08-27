package com.cpp.servicebooking.repository;


import com.cpp.servicebooking.models.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long> {

    Role findByName(String name);

    //@Query("SELECT role FROM Role role WHERE role.id=:x")
    //Role findRoleById(@Param("x") Long id);
}
