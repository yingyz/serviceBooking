package com.cpp.servicebooking.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    private String commentDetail;
    private String title;
    private String info;
    private Boolean active;
    private UserDto requestUser;
    private UserDto userdto;
}
