package com.cpp.servicebooking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
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
}
