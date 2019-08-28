package com.cpp.servicebooking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please enter the role Name")
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "serviceType", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ServiceProvide> serviceProvides = new HashSet<>();

    public ServiceType() {}

    public ServiceType(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ServiceProvide> getServiceProvides() {
        return serviceProvides;
    }

    public void setServiceProvides(Set<ServiceProvide> serviceProvides) {
        this.serviceProvides = serviceProvides;
    }
}
