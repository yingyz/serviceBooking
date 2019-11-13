package com.cpp.servicebooking.Response;

import com.cpp.servicebooking.models.dto.ServiceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServicesResponse {
    private List<ServiceDto> serviceDtoList;
    private int size;
}
