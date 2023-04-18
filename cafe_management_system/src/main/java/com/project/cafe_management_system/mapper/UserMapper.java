package com.project.cafe_management_system.mapper;

import com.project.cafe_management_system.dto.UserDTO;
import com.project.cafe_management_system.model.User;
import com.project.cafe_management_system.service.IdentifyService;
import com.project.cafe_management_system.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private IdentifyService identifyService;

    @Autowired
    private UserRoleService userRoleService;

    public User convertDTOToModel(UserDTO userDTO){
        User user = new User();

        user.setId(userDTO.getId());
        user.setIdentify(identifyService.retrievedByUsername(userDTO.getIdentifyId()));
        user.setName(userDTO.getName());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setAddress(userDTO.getAddress());
        user.setUserRole(userRoleService.retrievedById(userDTO.getUserRoleId()));
        user.setStatus(userDTO.getStatus());

        return user;
    }

    public UserDTO convertModelToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setIdentifyId(user.getIdentify().getUsername());
        userDTO.setName(user.getName());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setAddress(user.getAddress());
        userDTO.setUserRoleId(user.getUserRole().getId());
        userDTO.setStatus(user.getStatus());

        return userDTO;
    }

}
