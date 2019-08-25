package com.cpp.servicebooking.repository;

import com.cpp.servicebooking.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User getById(Long id);
}
