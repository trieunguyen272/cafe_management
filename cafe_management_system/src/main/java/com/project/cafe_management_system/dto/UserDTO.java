package com.project.cafe_management_system.dto;

import com.project.cafe_management_system.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    private String identifyId;

    private String name;

    private Date dateOfBirth;

    private String address;

    private Long userRoleId;

    private Status status;

}
