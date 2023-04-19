package com.project.cafe_management_system.mapper;

import com.project.cafe_management_system.dto.UserDTO;
import com.project.cafe_management_system.model.Identify;
import com.project.cafe_management_system.model.User;
import com.project.cafe_management_system.model.UserRole;
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

//    public User convertDTOToModel(UserDTO userDTO) {
//        User.UserBuilder userBuilder = User.builder();
//        userBuilder.id(userDTO.getId());
//
//        Identify identify = new Identify();
//        identify.setUsername(userDTO.getIdentifyId());
//        userBuilder.identify(identify);
//
//        userBuilder.name(userDTO.getName());
//        userBuilder.dateOfBirth(userDTO.getDateOfBirth());
//        userBuilder.address(userDTO.getAddress());
//
//        UserRole userRole = new UserRole();
//        userRole.setId(userDTO.getUserRoleId());
//        userBuilder.userRole(userRole);
//
//        userBuilder.status(userDTO.getStatus());
//
//        return userBuilder.build();
//    }

//    public User convertDTOToModel(UserDTO userDTO, Identify identify, UserRole userRole) {
//        return User.builder()
//                .id(userDTO.getId())
//                .identify(identify)
//                .name(userDTO.getName())
//                .dateOfBirth(userDTO.getDateOfBirth())
//                .address(userDTO.getAddress())
//                .userRole(userRole)
//                .status(userDTO.getStatus())
//                .build();
//    }

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
