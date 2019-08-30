package com.cpp.servicebooking.repository;

import com.cpp.servicebooking.models.RequestOrder;
import org.springframework.data.repository.CrudRepository;

public interface RequestOrderRepo extends CrudRepository<RequestOrder, Long> {
    RequestOrder findById(long id);
}
