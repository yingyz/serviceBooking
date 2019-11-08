package com.cpp.servicebooking.repository;

import com.cpp.servicebooking.models.User;
import com.cpp.servicebooking.models.dto.UserDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User getById(Long id);
}
