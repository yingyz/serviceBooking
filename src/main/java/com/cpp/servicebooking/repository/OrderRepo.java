package com.cpp.servicebooking.repository;

import com.cpp.servicebooking.models.OrderRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends CrudRepository<OrderRequest, Long> {


}
