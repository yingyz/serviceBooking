package com.cpp.servicebooking.repository;

import com.cpp.servicebooking.models.Language;
import com.cpp.servicebooking.models.ServiceProvide;
import com.cpp.servicebooking.models.ServiceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Repository
public interface ServiceProvideRepo extends CrudRepository<ServiceProvide, Long> {
    ArrayList<ServiceProvide> findAllByServiceType(ServiceType serviceType);
    ArrayList<ServiceProvide> findAllByLanguage(Language language);
    ArrayList<ServiceProvide> findAllByServiceTypeAndLanguage(ServiceType serviceType, Language language);
}
