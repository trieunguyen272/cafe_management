package com.project.cafe_management_system.utils;

import com.project.cafe_management_system.model.Identify;
import com.project.cafe_management_system.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;

    private Identify identify;
    private User user;
}
