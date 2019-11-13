package com.cpp.servicebooking.repository;

import com.cpp.servicebooking.models.Language;
import com.cpp.servicebooking.models.ServiceProvide;
import com.cpp.servicebooking.models.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public interface ServiceProvideRepo extends PagingAndSortingRepository<ServiceProvide, Long> {
    //Service
    ArrayList<ServiceProvide> findAllByServiceType(ServiceType serviceType);
    ArrayList<ServiceProvide> findAllByLanguage(Language language);
    ArrayList<ServiceProvide> findAllByServiceTypeAndLanguage(ServiceType serviceType, Language language);

    //page
    Page<ServiceProvide> findAll(Pageable pageableRequest);
    Page<ServiceProvide> findAllByServiceType(ServiceType serviceType, Pageable pageableRequest);
    Page<ServiceProvide> findAllByLanguage(Language language, Pageable pageableRequest);
    Page<ServiceProvide> findAllByServiceTypeAndLanguage(ServiceType serviceType, Language language, Pageable pageableRequest);
}
