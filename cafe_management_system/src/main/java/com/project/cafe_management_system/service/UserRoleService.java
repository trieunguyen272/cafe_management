package com.project.cafe_management_system.service;

import com.project.cafe_management_system.dto.UserRoleDTO;
import com.project.cafe_management_system.mapper.UserRoleMapper;
import com.project.cafe_management_system.model.UserRole;
import com.project.cafe_management_system.repository.UserRoleRepository;
import com.project.cafe_management_system.utils.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public ResponseGeneric<UserRoleDTO> createUserRole(UserRoleDTO userRoleDTO) {
        UserRole userRole = userRoleMapper.convertDTOToModel(userRoleDTO);

        userRole = userRoleRepository.save(userRole);

        UserRoleDTO savedUserRoleDTO = userRoleMapper.convertModelToDTO(userRole);

        return new ResponseGeneric<>(200, "success", savedUserRoleDTO);
    }

    public ResponseGeneric<List<UserRoleDTO>> getAllUserRole() {
        List<UserRole> userRoles = userRoleRepository.findAll();

        List<UserRoleDTO> userRoleDTOs = userRoles.stream()
                .map(userRole -> userRoleMapper.convertModelToDTO(userRole))
                .collect(Collectors.toList());

        return new ResponseGeneric<>(200, "success", userRoleDTOs);
    }




}
