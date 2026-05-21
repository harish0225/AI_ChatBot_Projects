package com.Quantam.Text_Generation_By_AI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Quantam.Text_Generation_By_AI.service.ImageService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ImageController {

    @Autowired
    private ImageService service;

    @PostMapping("/analyze")
    public String analyzeImage(
            @RequestParam("image")
            MultipartFile image)
            throws Exception {

        return service.getImageInfo(image);
    }
}