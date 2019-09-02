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
import com.cpp.servicebooking.util.DistanceCalculator;
import com.cpp.servicebooking.util.ServiceProvideComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ServiceProvideService {

    @Autowired
    private ServiceTypeRepo serviceTypeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ServiceProvideRepo serviceProvideRepo;

    @Autowired
    private DistanceCalculator distanceCalculator;

    @Autowired
    private ServiceProvideComparator serviceProvideComparator;


    public ServiceProvide updateService(ServiceProvideRequest serviceProvideRequest, String name) {
        User user = userRepo.findByUsername(name);
        ServiceProvide serviceProvide = user.getServiceProvide();
        if (serviceProvide == null) {
            serviceProvide = new ServiceProvide();
            user.setServiceProvide(serviceProvide);
        }
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

    public List<ServiceProvide> getServicesByName(String serviceName, String name) {
        ServiceType serviceType = serviceTypeRepo.findByName(serviceName);
        if (serviceType == null) {
            throw new DatabaseNotFoundException("Service not found in database");
        }

        User user = userRepo.findByUsername(name);
        int zip = user.getUserInfo().getZipcode();
        List<ServiceProvide> ans = serviceProvideRepo.findAllByServiceType(serviceType);

        for (ServiceProvide serviceProvide : ans) {
            double dis = distanceCalculator.getDistance(zip, serviceProvide.getUser().getUserInfo().getZipcode());
            serviceProvide.getUser().getUserInfo().setDistance(dis);
        }

        Collections.sort(ans, serviceProvideComparator);

        return ans;
    }
}
