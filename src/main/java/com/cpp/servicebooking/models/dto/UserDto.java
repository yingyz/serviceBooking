package com.cpp.servicebooking.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private String username;

    private String firstname;

    private String lastname;

    private String streetname;

    private String city;

    private String state;

    private Integer zipcode;

    private String phone;

    private String language;

}
