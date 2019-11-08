package com.cpp.servicebooking.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDto {

    private Long requestId;
    private String title;
    private String info;
    private Boolean active;
    private UserDto userDto;
    private Date create_At;
    private Date update_At;
}
