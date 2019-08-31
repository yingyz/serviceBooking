package com.cpp.servicebooking.repository;

import com.cpp.servicebooking.models.RequestOrder;
import com.cpp.servicebooking.models.User;
import org.springframework.data.repository.CrudRepository;


public interface RequestOrderRepo extends CrudRepository<RequestOrder, Long> {
    RequestOrder findById(long id);

    Iterable<RequestOrder> findAllByActive(boolean active);

    Iterable<RequestOrder> findAllByActiveAndUser(boolean active, User user);
}
