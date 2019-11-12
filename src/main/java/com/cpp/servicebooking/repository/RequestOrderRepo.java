package com.cpp.servicebooking.repository;

import com.cpp.servicebooking.models.Language;
import com.cpp.servicebooking.models.RequestOrder;
import com.cpp.servicebooking.models.ServiceType;
import com.cpp.servicebooking.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface RequestOrderRepo extends PagingAndSortingRepository<RequestOrder, Long> {
    RequestOrder findById(long id);

    /*
    * Requests
    * */
    List<RequestOrder> findAllByServiceType(ServiceType serviceType);
    List<RequestOrder> findAllByLanguage(Language language);
    List<RequestOrder> findAllByServiceTypeAndLanguage(ServiceType serviceType, Language language);
    //Iterable<RequestOrder> findAllByActive(boolean active);
    //Iterable<RequestOrder> findAllByActiveAndUser(boolean active, User user);

    /*
    * Page
    * */
    //@Query("SELECT r FROM RequestOrder r")
    //Page<RequestOrder> findAllRequest(Pageable pageableRequest);
    Page<RequestOrder> findAll(Pageable pageableRequest);
    Page<RequestOrder> findAllByUser(User user, Pageable pageableRequest);
    Page<RequestOrder> findAllByServiceType(ServiceType serviceType, Pageable pageableRequest);
    Page<RequestOrder> findAllByLanguage(Language language, Pageable pageableRequest);
    Page<RequestOrder> findAllByServiceTypeAndLanguage(ServiceType serviceType, Language language, Pageable pageableRequest);

    @Modifying
    @Transactional
    @Query("UPDATE RequestOrder r set r.language =:l where r.user =:u")
    void updateRequestOrderLanguage(
            @Param("l") Language language,
            @Param("u") User user);
}

