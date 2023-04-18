package com.project.cafe_management_system.controller;

import com.project.cafe_management_system.dto.UserDTO;
import com.project.cafe_management_system.service.UserService;
import com.project.cafe_management_system.utils.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ResponseGeneric<UserDTO>> createDTO(@RequestBody UserDTO userDTO){
        ResponseGeneric<UserDTO> userDTOs = userService.createUser(userDTO);

        return ResponseEntity.ok(userDTOs);
    }

}
