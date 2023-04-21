package com.project.cafe_management_system.service;

import java.util.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    public String encodeImageToBase64(MultipartFile imageFile) {
        try {
            byte[] imageBytes = imageFile.getBytes();
            Base64.Encoder encoder = Base64.getEncoder();
            String encodedString = encoder.encodeToString(imageBytes);
            return encodedString;
        } catch (Exception e) {
            // Handle exception
        }
        return null;
    }
}