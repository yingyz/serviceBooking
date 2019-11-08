package com.cpp.servicebooking.repository;


import com.cpp.servicebooking.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long> {

    Role findByName(String name);

    //@Query("SELECT role FROM Role role WHERE role.id=:x")
    //Role findRoleById(@Param("x") Long id);
}
