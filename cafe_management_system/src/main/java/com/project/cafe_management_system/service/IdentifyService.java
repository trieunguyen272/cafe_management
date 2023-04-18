package com.project.cafe_management_system.service;

import com.project.cafe_management_system.dto.IdentifyDTO;
import com.project.cafe_management_system.exception.ResourceNotFoundException;
import com.project.cafe_management_system.mapper.IdentifyMapper;
import com.project.cafe_management_system.model.Identify;
import com.project.cafe_management_system.repository.IdentifyRepository;
import com.project.cafe_management_system.utils.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IdentifyService {

    @Autowired
    private IdentifyMapper identifyMapper;

    @Autowired
    private IdentifyRepository identifyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Identify retrievedByUsername(String username) {
        Identify identify = identifyRepository.findById(username).orElseThrow(() -> new ResourceNotFoundException("Identify not exist with username: " + username));

        return identify;
    }

    public ResponseGeneric<IdentifyDTO> createIdentify(IdentifyDTO identifyDTO) {
        Identify identify = identifyRepository.findIdentifyByUsername(identifyDTO.getUsername());
        if (identify != null) {
            throw new ResourceNotFoundException("Identify already exist with username: " + identifyDTO.getUsername());
        }
        identify = identifyRepository.save(identifyMapper.convertDTOToModel(identifyDTO));
        IdentifyDTO savedIdentifyDTO = identifyMapper.convertModelToDTO(identify);

        return new ResponseGeneric<>(200, "success", savedIdentifyDTO);

    }

    public ResponseGeneric<List<IdentifyDTO>> getAllIdentify() {
        List<Identify> identifies = identifyRepository.findAll();
        List<IdentifyDTO> identifyDTOs = identifies.stream()
                .map(identify -> identifyMapper.convertModelToDTO(identify))
                .collect(Collectors.toList());

        return new ResponseGeneric<>(200, "success", identifyDTOs);
    }


}
