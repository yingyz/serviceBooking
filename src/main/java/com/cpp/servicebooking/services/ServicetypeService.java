package com.cpp.servicebooking.services;

import com.cpp.servicebooking.Request.AdminRequest.ServiceTypeRequest;
import com.cpp.servicebooking.exceptions.Exception.DatabaseNotFoundException;
import com.cpp.servicebooking.exceptions.Exception.DuplicateAccountException;
import com.cpp.servicebooking.models.ServiceType;
import com.cpp.servicebooking.repository.ServiceTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicetypeService {

    @Autowired
    private ServiceTypeRepo serviceTypeRepo;

    public ServiceType findServiceByServicename(String serviceName) {
        ServiceType serviceType = serviceTypeRepo.findByName(serviceName);
        if (serviceType == null) {
            throw new DatabaseNotFoundException("Service not found in database");
        }
        return serviceType;
    }

    public ServiceType saveServicetype(ServiceTypeRequest serviceTypeRequest) {
        try {
            ServiceType serviceType = new ServiceType();
            serviceType.setName(serviceTypeRequest.getName());
            return  serviceTypeRepo.save(serviceType);
        } catch (Exception e) {
            throw new DuplicateAccountException("Service name '"+ serviceTypeRequest.getName() +"' already exists");
        }
    }

    public List<ServiceType> findAllServiceTypes() {
        return serviceTypeRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}
