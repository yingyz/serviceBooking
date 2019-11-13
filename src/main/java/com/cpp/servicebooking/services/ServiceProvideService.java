package com.cpp.servicebooking.services;

import com.cpp.servicebooking.Request.ServiceRequest.ServiceProvideRequest;
import com.cpp.servicebooking.Response.ServicesResponse;
import com.cpp.servicebooking.exceptions.Exception.DatabaseNotFoundException;
import com.cpp.servicebooking.models.Language;
import com.cpp.servicebooking.models.ServiceProvide;
import com.cpp.servicebooking.models.ServiceType;
import com.cpp.servicebooking.models.User;
import com.cpp.servicebooking.models.dto.ServiceDto;
import com.cpp.servicebooking.repository.LanguageRepo;
import com.cpp.servicebooking.repository.ServiceProvideRepo;
import com.cpp.servicebooking.repository.ServiceTypeRepo;
import com.cpp.servicebooking.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceProvideService {

    @Autowired
    private ServiceTypeRepo serviceTypeRepo;

    @Autowired
    private LanguageRepo languageRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ServiceProvideRepo serviceProvideRepo;

    @Autowired
    private UserService userService;

    public ServiceDto updateService(ServiceProvideRequest serviceProvideRequest, String name) {
        User user = userRepo.findByUsername(name);
        ServiceProvide serviceProvide = user.getServiceProvide();
        if (serviceProvide == null) {
            serviceProvide = new ServiceProvide();
            serviceProvide.setUser(user);//important
            user.setServiceProvide(serviceProvide);
        }
        serviceProvide.setDetail(serviceProvideRequest.getDetail());
        serviceProvide.setPrice(serviceProvideRequest.getPrice());
        ServiceType serviceType = serviceTypeRepo.findByName(serviceProvideRequest.getServicename());
        if (serviceType == null) {
            throw new DatabaseNotFoundException("Service not found in database");
        }
        serviceProvide.setServiceType(serviceType);
        serviceProvide.setLanguage(user.getUserInfo().getLanguage());

        serviceProvideRepo.save(serviceProvide);
        ServiceDto serviceDto = transferServiceDto(serviceProvide);

        return serviceDto;
    }

    public ServiceDto getServiceByUserName(String name) {
        User user = userRepo.findByUsername(name);
        ServiceProvide serviceProvide = user.getServiceProvide();
        if (serviceProvide == null) return null;
        return transferServiceDto(serviceProvide);
    }

    public ServicesResponse getServices(int page, int limit) {
        List<ServiceProvide> allServiceProvides = (ArrayList)serviceProvideRepo.findAll();
        int size = allServiceProvides.size();

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<ServiceProvide> page1 = serviceProvideRepo.findAll(pageableRequest);
        List<ServiceProvide> serviceProvides = page1.getContent();

        return new ServicesResponse(transferServiceDtos(serviceProvides), size);
    }

    public ServicesResponse getServicesByName(String serviceName, int page, int limit) {
        ServiceType serviceType = serviceTypeRepo.findByName(serviceName);
        if (serviceType == null) {
            throw new DatabaseNotFoundException("Service not found in database");
        }

        List<ServiceProvide> allServiceProvides = serviceProvideRepo.findAllByServiceType(serviceType);
        int size = allServiceProvides.size();

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<ServiceProvide> page1 = serviceProvideRepo.findAllByServiceType(serviceType, pageableRequest);
        List<ServiceProvide> serviceProvides = page1.getContent();

        return new ServicesResponse(transferServiceDtos(serviceProvides), size);
    }

    public ServicesResponse getServiceByLanguage(String languageName, int page, int limit) {
        Language language = languageRepo.findByName(languageName);
        if (language == null) {
            throw new DatabaseNotFoundException("Service not found in database");
        }

        List<ServiceProvide> allServiceProvides = serviceProvideRepo.findAllByLanguage(language);
        int size = allServiceProvides.size();

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<ServiceProvide> page1 = serviceProvideRepo.findAllByLanguage(language, pageableRequest);
        List<ServiceProvide> serviceProvides = page1.getContent();

        return new ServicesResponse(transferServiceDtos(serviceProvides), size);
    }

    public ServicesResponse getServiceByNameAndService(String serviceName, String languageName, int page, int limit) {
        ServiceType serviceType = serviceTypeRepo.findByName(serviceName);
        if (serviceType == null) {
            throw new DatabaseNotFoundException("Service not found in database");
        }
        Language language = languageRepo.findByName(languageName);
        if (language == null) {
            throw new DatabaseNotFoundException("Service not found in database");
        }

        List<ServiceProvide> allServiceProvides = serviceProvideRepo.findAllByServiceTypeAndLanguage(serviceType, language);
        int size = allServiceProvides.size();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<ServiceProvide> page1 = serviceProvideRepo.findAllByServiceTypeAndLanguage(serviceType, language, pageableRequest);
        List<ServiceProvide> serviceProvides = page1.getContent();

        return new ServicesResponse(transferServiceDtos(serviceProvides), size);
    }

    private List<ServiceDto> transferServiceDtos(List<ServiceProvide> serviceProvides) {
        List<ServiceDto> serviceDtos = new ArrayList<>();
        for (ServiceProvide serviceProvide : serviceProvides) {
            serviceDtos.add(transferServiceDto(serviceProvide));
        }
        return serviceDtos;
    }

    private ServiceDto transferServiceDto(ServiceProvide serviceProvide) {
        ServiceDto serviceDto = ServiceDto.builder()
                .detail(serviceProvide.getDetail())
                .price(serviceProvide.getPrice())
                .serviceId(serviceProvide.getId())
                .servicetype(serviceProvide.getServiceType().getName())
                .userDto(userService.transferUserDto(serviceProvide.getUser()))
                .build();
        return serviceDto;
    }
}
