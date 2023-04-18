package com.project.cafe_management_system.service;

import com.project.cafe_management_system.dto.UserDTO;
import com.project.cafe_management_system.exception.ResourceNotFoundException;
import com.project.cafe_management_system.mapper.UserMapper;
import com.project.cafe_management_system.model.User;
import com.project.cafe_management_system.repository.UserRepository;
import com.project.cafe_management_system.utils.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public ResponseGeneric<UserDTO> createUser(UserDTO userDTO){
//        User user = userRepository.findUserByUsername(userDTO.getIdentifyId());
//        if (user != null) {
//            throw new ResourceNotFoundException("User already exist with username: " + userDTO.getIdentifyId());
//        }
        User user = userRepository.save(userMapper.convertDTOToModel(userDTO));

        UserDTO savedUserDTO = userMapper.convertModelToDTO(user);

        return new ResponseGeneric<>(200, "success", savedUserDTO);
    }

}
