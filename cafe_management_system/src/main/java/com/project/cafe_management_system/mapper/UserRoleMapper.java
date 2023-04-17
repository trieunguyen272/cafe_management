package com.project.cafe_management_system.mapper;

import com.project.cafe_management_system.dto.UserRoleDTO;
import com.project.cafe_management_system.model.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper {
    public UserRole convertDTOToModel(UserRoleDTO userRoleDTO) {
        UserRole userRole = new UserRole();

        userRole.setId(userRoleDTO.getId());
        userRole.setRoleName(userRoleDTO.getRoleName());

        return userRole;
    }

    public UserRoleDTO convertModelToDTO(UserRole userRole) {
        UserRoleDTO userRoleDTO = new UserRoleDTO();

        userRoleDTO.setId(userRole.getId());
        userRoleDTO.setRoleName(userRole.getRoleName());

        return userRoleDTO;
    }
}
