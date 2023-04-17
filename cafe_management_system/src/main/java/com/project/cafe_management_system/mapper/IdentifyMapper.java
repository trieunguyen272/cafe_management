package com.project.cafe_management_system.mapper;

import com.project.cafe_management_system.dto.IdentifyDTO;
import com.project.cafe_management_system.model.Identify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class IdentifyMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public IdentifyDTO convertModelToDTO (Identify identify) {
        IdentifyDTO identifyDTO = new IdentifyDTO();

        identifyDTO.setUsername(identify.getUsername());

        identifyDTO.setPassword(passwordEncoder.encode(identify.getPassword()));

        return identifyDTO;
    }

    public Identify convertDTOToModel (IdentifyDTO identifyDTO) {
        Identify identify = new Identify();

        identify.setUsername(identifyDTO.getUsername());
        identify.setPassword(passwordEncoder.encode(identifyDTO.getPassword()));

        return identify;
    }
}
