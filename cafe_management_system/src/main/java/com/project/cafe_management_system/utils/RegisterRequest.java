package com.project.cafe_management_system.utils;

import com.project.cafe_management_system.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private String name;
    private String address;
    private Date dateOfBirth;
    private Long userRoleId;
    private Status status;

}