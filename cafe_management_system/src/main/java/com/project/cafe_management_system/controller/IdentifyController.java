package com.project.cafe_management_system.controller;

import com.project.cafe_management_system.dto.IdentifyDTO;
import com.project.cafe_management_system.service.IdentifyService;
import com.project.cafe_management_system.utils.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/identify")
public class IdentifyController {
    @Autowired
    private IdentifyService identifyService;


    @PostMapping
    public ResponseEntity<ResponseGeneric<IdentifyDTO>> createIdentify(@RequestBody IdentifyDTO identifyDTO) {
        ResponseGeneric<IdentifyDTO> identifyDTOs = identifyService.createIdentify(identifyDTO);

        return ResponseEntity.ok(identifyDTOs);
    }

    @GetMapping
    public ResponseEntity<ResponseGeneric<List<IdentifyDTO>>> getAllIdentify() {
        ResponseGeneric<List<IdentifyDTO>> identifyDTOs = identifyService.getAllIdentify();

        return ResponseEntity.ok(identifyDTOs);
    }


}
