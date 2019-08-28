package com.cpp.servicebooking.services;

import com.cpp.servicebooking.exceptions.DuplicateAccountException;
import com.cpp.servicebooking.models.ServiceType;
import com.cpp.servicebooking.repository.ServiceTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicetypeService {

    @Autowired
    private ServiceTypeRepo serviceTypeRepo;

    public ServiceType findServiceByServicename(String serviceName) {
        ServiceType serviceType = serviceTypeRepo.findByName(serviceName);
        return serviceType;
    }

    public ServiceType saveServicetype(ServiceType serviceType) {
        try {
            serviceType.setName(serviceType.getName());
            return  serviceTypeRepo.save(serviceType);
        } catch (Exception e) {
            throw new DuplicateAccountException("Service name '"+ serviceType.getName() +"' already exists");
        }
    }
}
