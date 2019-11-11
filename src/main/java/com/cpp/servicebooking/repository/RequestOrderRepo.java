package com.cpp.servicebooking.repository;

import com.cpp.servicebooking.models.Language;
import com.cpp.servicebooking.models.RequestOrder;
import com.cpp.servicebooking.models.ServiceType;
import com.cpp.servicebooking.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RequestOrderRepo extends CrudRepository<RequestOrder, Long> {
    RequestOrder findById(long id);

    Iterable<RequestOrder> findAllByActive(boolean active);

    Iterable<RequestOrder> findAllByActiveAndUser(boolean active, User user);

    List<RequestOrder> findAllByServiceType(ServiceType serviceType);

    List<RequestOrder> findAllByLanguage(Language language);

    List<RequestOrder> findAllByServiceTypeAndLanguage(ServiceType serviceType, Language language);


}

