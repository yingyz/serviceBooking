package com.cpp.servicebooking.Response;

import com.cpp.servicebooking.models.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JWTLoginSucessReponse {
    private String token;
    private UserDto user;
}
