package com.cpp.servicebooking.repository;

import com.cpp.servicebooking.models.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepo extends JpaRepository<ServiceType, Long> {
    ServiceType findByName(String name);
}
