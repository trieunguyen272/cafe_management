package com.project.cafe_management_system.service;

import com.project.cafe_management_system.dto.IdentifyDTO;
import com.project.cafe_management_system.dto.UserDTO;
import com.project.cafe_management_system.exception.ResourceNotFoundException;
import com.project.cafe_management_system.mapper.IdentifyMapper;
import com.project.cafe_management_system.mapper.UserMapper;
import com.project.cafe_management_system.model.Identify;
import com.project.cafe_management_system.model.User;
import com.project.cafe_management_system.repository.IdentifyRepository;
import com.project.cafe_management_system.repository.UserRepository;
import com.project.cafe_management_system.utils.AuthenticationRequest;
import com.project.cafe_management_system.utils.AuthenticationResponse;
import com.project.cafe_management_system.utils.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IdentifyRepository identifyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IdentifyMapper identifyMapper;

    @Autowired
    private UserMapper userMapper;

    public AuthenticationResponse register(RegisterRequest request) {

        Identify identify = identifyRepository.findIdentifyByUsername(request.getUsername());
        if (identify != null) {
            throw new ResourceNotFoundException("Identify already exist with username: " + request.getUsername());
        }

        IdentifyDTO identifyUserDTO = IdentifyDTO.builder()
                                    .username(request.getUsername())
                                    .password(passwordEncoder.encode(request.getPassword()))
                                    .build();

        UserDTO userDTO = UserDTO.builder()
                .identifyId(request.getUsername())
                .name(request.getName())
                .address(request.getAddress())
                .dateOfBirth(request.getDateOfBirth())
                .userRoleId(request.getUserRoleId())
                .status(request.getStatus())
                .build();

        identify = identifyRepository.save(identifyMapper.convertDTOToModel(identifyUserDTO));
        User user = userRepository.save(userMapper.convertDTOToModel(userDTO));
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .identify(identify)
                .user(user)
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Identify identify = identifyRepository.findIdentifyByIdentifyName(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Identify not exist with username: " + request.getUsername()));;
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        }
        catch(Exception e) {
            throw new ResourceNotFoundException("Password wrong.");
        }
        var jwtToken = jwtService.generateToken(identify);

        return AuthenticationResponse.builder()
                .identify(identify)
                .token(jwtToken)
                .build();
    }

}
