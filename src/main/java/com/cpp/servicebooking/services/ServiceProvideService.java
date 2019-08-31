package com.cpp.servicebooking.services;

import com.cpp.servicebooking.Request.ServiceRequest.ServiceProvideRequest;
import com.cpp.servicebooking.exceptions.Exception.DatabaseNotFoundException;
import com.cpp.servicebooking.models.RequestOrder;
import com.cpp.servicebooking.models.ServiceProvide;
import com.cpp.servicebooking.models.ServiceType;
import com.cpp.servicebooking.models.User;
import com.cpp.servicebooking.repository.ServiceProvideRepo;
import com.cpp.servicebooking.repository.ServiceTypeRepo;
import com.cpp.servicebooking.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceProvideService {

    @Autowired
    private ServiceTypeRepo serviceTypeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ServiceProvideRepo serviceProvideRepo;


    public ServiceProvide updateService(ServiceProvideRequest serviceProvideRequest, String name) {
        User user = userRepo.findByUsername(name);
        ServiceProvide serviceProvide = user.getServiceProvide();
        serviceProvide.setDetail(serviceProvideRequest.getDetail());
        serviceProvide.setPrice(serviceProvideRequest.getPrice());
        ServiceType serviceType = serviceTypeRepo.findByName(serviceProvideRequest.getServicename());
        if (serviceType == null) {
            throw new DatabaseNotFoundException("Service not found in database");
        }
        serviceProvide.setServiceType(serviceType);
        serviceProvideRepo.save(serviceProvide);

        return serviceProvide;
    }

    public Iterable<ServiceProvide> getServicesByName(String name) {
        name = name.replaceAll("_", " ");
        ServiceType serviceType = serviceTypeRepo.findByName(name);
        if (serviceType == null) {
            throw new DatabaseNotFoundException("Service not found in database");
        }
        return serviceProvideRepo.findAllByServiceType(serviceType);
    }
}
