//package com.QuantamParadigm.ImageGenerator.Controller;
//
//
//
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.QuantamParadigm.ImageGenerator.DTO.ImageRequest;
//import com.QuantamParadigm.ImageGenerator.DTO.ImageResponse;
//import com.QuantamParadigm.ImageGenerator.service.ImageService;
//
//@RestController  // No HTML - pure JSON responses only
//@RequestMapping("/api")
//public class ImageController {
//
//    private final ImageService imageService;
//
//    public ImageController(ImageService imageService) {
//        this.imageService = imageService;
//    }
//
//    // Health check - test if server is running
//    @GetMapping("/health")
//    public ResponseEntity<ImageResponse> health() {
//        return ResponseEntity.ok(
//                new ImageResponse("success", null, "Server is running!")
//        );
//    }
//
//    
//    @PostMapping("/api/generate/save")
//    @ResponseBody
//    public ResponseEntity<ImageResponse> generateAndSave(@RequestBody ImageRequest request) {
//        try {
//            String base64Image = imageService.generateImage(request.getPrompt());
//
//            // Save directly to your Desktop
//            byte[] imageBytes = java.util.Base64.getDecoder().decode(base64Image);
//            java.nio.file.Files.write(
//                java.nio.file.Path.of("C:/Users/haris/Desktop/generated_image.jpg"),
//                imageBytes
//            );
//
//            return ResponseEntity.ok(
//                new ImageResponse("success", base64Image, "Image saved to your Desktop!")
//            );
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError()
//                .body(new ImageResponse("error", null, e.getMessage()));
//        }
//    }
//    // Generate image from JSON prompt
//    @PostMapping("/generate")
//    public ResponseEntity<ImageResponse> generate(@RequestBody ImageRequest request) {
//        try {
//            // Validate prompt
//            if (request.getPrompt() == null || request.getPrompt().isBlank()) {
//                return ResponseEntity
//                        .badRequest()
//                        .body(new ImageResponse("error", null, "Prompt cannot be empty"));
//            }
//
//            // Call Hugging Face API
//            String base64Image = imageService.generateImage(request.getPrompt());
//
//            return ResponseEntity.ok(
//                    new ImageResponse("success", base64Image, "Image generated successfully")
//            );
//
//        } catch (Exception e) {
//            return ResponseEntity
//                    .internalServerError()
//                    .body(new ImageResponse("error", null, e.getMessage()));
//        }
//    }
//}



//package com.QuantamParadigm.ImageGenerator.Controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.QuantamParadigm.ImageGenerator.DTO.ImageRequest;
//import com.QuantamParadigm.ImageGenerator.DTO.ImageResponse;
//import com.QuantamParadigm.ImageGenerator.service.ImageService;
//
//@RestController  // Automatically applies @ResponseBody to all methods
//@RequestMapping("/api")
//public class ImageController {
//
//    private final ImageService imageService;
//
//    public ImageController(ImageService imageService) {
//        this.imageService = imageService;
//    }
//
//    // Health check - test if server is running
//    @GetMapping("/health")
//    public ResponseEntity<ImageResponse> health() {
//        return ResponseEntity.ok(
//                new ImageResponse("success", null, "Server is running!")
//        );
//    }
//
//    // Fixed route mapping conflict (Changed from "/api/generate/save" to "/generate/save")
//    @PostMapping("/generate/save")
//    public ResponseEntity<ImageResponse> generateAndSave(@RequestBody ImageRequest request) {
//        try {
//            if (request.getPrompt() == null || request.getPrompt().isBlank()) {
//                return ResponseEntity.badRequest()
//                        .body(new ImageResponse("error", null, "Prompt cannot be empty"));
//            }
//
//            String base64Image = imageService.generateImage(request.getPrompt());
//
//            // Save directly to Desktop
//            byte[] imageBytes = java.util.Base64.getDecoder().decode(base64Image);
//            java.nio.file.Files.write(
//                java.nio.file.Path.of("C:/Users/haris/Desktop/generated_image.jpg"),
//                imageBytes
//            );
//
//            return ResponseEntity.ok(
//                new ImageResponse("success", base64Image, "Image saved to your Desktop!")
//            );
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError()
//                .body(new ImageResponse("error", null, e.getMessage()));
//        }
//    }
//
//    // Generate image from JSON prompt
//    @PostMapping("/generate")
//    public ResponseEntity<ImageResponse> generate(@RequestBody ImageRequest request) {
//        try {
//            // Validate prompt
//            if (request.getPrompt() == null || request.getPrompt().isBlank()) {
//                return ResponseEntity.badRequest()
//                        .body(new ImageResponse("error", null, "Prompt cannot be empty"));
//            }
//
//            // Call Hugging Face API
//            String base64Image = imageService.generateImage(request.getPrompt());
//
//            return ResponseEntity.ok(
//                    new ImageResponse("success", base64Image, "Image generated successfully")
//            );
//
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError()
//                    .body(new ImageResponse("error", null, e.getMessage()));
//        }
//    }
//}




package com.Quantam.Text_Generation_By_AI.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Quantam.Text_Generation_By_AI.DTO.ImageGenerationRequest;
import com.Quantam.Text_Generation_By_AI.DTO.ImageGenerationResponse;
import com.Quantam.Text_Generation_By_AI.service.ImageGenerationService;


@RestController
//@RequestMapping("/api")
@RequestMapping("/api/image")
@CrossOrigin(origins = "http://localhost:5173")
public class ImageGenerationController {

    private final ImageGenerationService imageService;

    public ImageGenerationController(ImageGenerationService imageService) {
        this.imageService = imageService;
    }

    // Health check
    @GetMapping("/health")
    public ResponseEntity<ImageGenerationResponse> health() {
        return ResponseEntity.ok(
                new ImageGenerationResponse("success", null, "Server is running!")
        );
    }

    // Generate image
    @PostMapping("/generate")
    public ResponseEntity<ImageGenerationResponse> generate(@RequestBody ImageGenerationRequest request) {
        try {

            if (request.getPrompt() == null || request.getPrompt().isBlank()) {
                return ResponseEntity.badRequest()
                        .body(new ImageGenerationResponse("error", null, "Prompt cannot be empty"));
            }

            String base64Image = imageService.generateImage(request.getPrompt());

            return ResponseEntity.ok(
                    new ImageGenerationResponse("success", base64Image, "Image generated successfully")
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ImageGenerationResponse("error", null, e.getMessage()));
        }
    }

    // Generate + Save to Desktop
    @PostMapping("/generate/save")
    public ResponseEntity<ImageGenerationResponse> generateAndSave(@RequestBody ImageGenerationRequest request) {
        try {

            if (request.getPrompt() == null || request.getPrompt().isBlank()) {
                return ResponseEntity.badRequest()
                        .body(new ImageGenerationResponse("error", null, "Prompt cannot be empty"));
            }

            String base64Image = imageService.generateImage(request.getPrompt());

            byte[] imageBytes = Base64.getDecoder().decode(base64Image);

            Path filePath = Path.of(
                    System.getProperty("user.home") + "/Desktop/generated_image.jpg"
            );

            Files.write(filePath, imageBytes);

            return ResponseEntity.ok(
                    new ImageGenerationResponse("success", base64Image, "Image saved to Desktop!")
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ImageGenerationResponse("error", null, e.getMessage()));
        }
    }
}