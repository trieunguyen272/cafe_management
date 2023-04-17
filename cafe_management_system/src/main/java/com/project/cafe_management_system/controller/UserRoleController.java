package com.project.cafe_management_system.controller;

import com.project.cafe_management_system.dto.UserRoleDTO;
import com.project.cafe_management_system.service.UserRoleService;
import com.project.cafe_management_system.utils.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user_role")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping
    public ResponseEntity<ResponseGeneric<UserRoleDTO>> createUserRole(@RequestBody UserRoleDTO userRoleDTO) {
        ResponseGeneric<UserRoleDTO> userRoleDTOs = userRoleService.createUserRole(userRoleDTO);

        return ResponseEntity.ok(userRoleDTOs);
    }

    @GetMapping
    public ResponseEntity<ResponseGeneric<List<UserRoleDTO>>> getAllUserRole() {
        ResponseGeneric<List<UserRoleDTO>> userRoleDTOs = userRoleService.getAllUserRole();

        return ResponseEntity.ok(userRoleDTOs);

    }

}
