package com.project.cafe_management_system.controller;


import com.project.cafe_management_system.dto.IdentifyDTO;
import com.project.cafe_management_system.service.AuthenticationService;
import com.project.cafe_management_system.utils.AuthenticationRequest;
import com.project.cafe_management_system.utils.AuthenticationResponse;
import com.project.cafe_management_system.utils.RegisterRequest;
import com.project.cafe_management_system.utils.ResponseGeneric;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
