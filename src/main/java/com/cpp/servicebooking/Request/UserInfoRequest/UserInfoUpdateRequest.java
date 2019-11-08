package com.cpp.servicebooking.Request.UserInfoRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoUpdateRequest {

    @NotBlank(message = "Name cannot be blank")
    private String firstname;

    @NotBlank(message = "Name cannot be blank")
    private String lastname;

    @NotBlank(message = "Street cannot be blank")
    private String streetname;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "State cannot be blank")
    private String state;

    @NotBlank(message = "Zipcode cannot be blank")
    private String zipcode;

    @NotBlank(message = "Phone number cannot be blank")
    private String phone;

    @NotBlank(message = "Language number cannot be blank")
    private String language;
}
