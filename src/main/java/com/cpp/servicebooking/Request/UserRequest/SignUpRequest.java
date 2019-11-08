package com.cpp.servicebooking.Request.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpRequest {
    @NotBlank(message = "Email cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "firstname cannot be blank")
    private String firstname;

    @NotBlank(message = "lastname cannot be blank")
    private String lastname;

    @NotBlank(message = "streetname cannot be blank")
    private String streetname;

    @NotBlank(message = "city cannot be blank")
    private String city;

    @NotBlank(message = "state cannot be blank")
    private String state;

    @NotBlank(message = "zipcode cannot be blank")
    private String zipcode;

    @NotBlank(message = "phone cannot be blank")
    private String phone;

    @NotBlank(message = "Role cannot be blank")
    private String role;

    @NotBlank(message = "Language cannot be blank")
    private String language;
}
