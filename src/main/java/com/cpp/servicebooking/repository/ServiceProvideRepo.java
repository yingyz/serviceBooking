package com.cpp.servicebooking.repository;

import com.cpp.servicebooking.models.ServiceProvide;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProvideRepo extends CrudRepository<ServiceProvide, Long> {
}
