//package com.QuantamParadigm.ImageGenerator.service;
//
//
//
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.net.URI;
//import java.net.http.*;
//import java.util.Base64;
//
//@Service
//public class ImageService {
//
//    @Value("${huggingface.api.key}")
//    private String apiKey;
//
//    // HARDCODED - no properties file needed
//    private final String apiUrl =
//        "https://router.huggingface.co/hf-inference/models/black-forest-labs/FLUX.1-schnell";
//
//    public String generateImage(String prompt) throws Exception {
//
//        System.out.println("=================================");
//        System.out.println(">>> URL  : " + apiUrl);
//        System.out.println(">>> KEY  : " + apiKey.substring(0, 8));
//        System.out.println("=================================");
//
//        String requestBody = "{\"inputs\": \"" + prompt + "\"}";
//
//        HttpClient client = HttpClient.newHttpClient();
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(apiUrl))
//                .header("Authorization", "Bearer " + apiKey)
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//                .build();
//
//        HttpResponse<byte[]> response = client.send(
//                request,
//                HttpResponse.BodyHandlers.ofByteArray()
//        );
//
//        System.out.println(">>> STATUS: " + response.statusCode());
//
//        if (response.statusCode() == 200) {
//            System.out.println(">>> SUCCESS!");
//            return Base64.getEncoder().encodeToString(response.body());
//        } else {
//            String error = new String(response.body());
//            System.out.println(">>> ERROR : " + error);
//            throw new RuntimeException("API Error " + response.statusCode() + ": " + error);
//        }
//    }
//}


//package com.QuantamParadigm.ImageGenerator.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.net.URI;
//import java.net.http.*;
//import java.util.Base64;
//
//@Service
//public class ImageService {
//
//    @Value("${huggingface.api.key}")
//    private String apiKey;
//
//    // Dynamically uses the property file URL instead of a hardcoded string
//    @Value("${huggingface.api.url}")
//    private String apiUrl;
//
//    public String generateImage(String prompt) throws Exception {
//
//        System.out.println("=================================");
//        System.out.println(">>> URL  : " + apiUrl);
//        if (apiKey != null && apiKey.length() > 8) {
//            System.out.println(">>> KEY  : " + apiKey.substring(0, 8) + "...");
//        }
//        System.out.println("=================================");
//
//        // Safely escape basic JSON characters for the prompt string
//        String escapedPrompt = prompt.replace("\"", "\\\"");
//        String requestBody = "{\"inputs\": \"" + escapedPrompt + "\"}";
//
//        HttpClient client = HttpClient.newHttpClient();
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(apiUrl))
//                .header("Authorization", "Bearer " + apiKey)
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//                .build();
//
//        HttpResponse<byte[]> response = client.send(
//                request,
//                HttpResponse.BodyHandlers.ofByteArray()
//        );
//
//        System.out.println(">>> STATUS: " + response.statusCode());
//
//        if (response.statusCode() == 200) {
//            System.out.println(">>> SUCCESS!");
//            return Base64.getEncoder().encodeToString(response.body());
//        } else {
//            String error = new String(response.body());
//            System.out.println(">>> ERROR : " + error);
//            throw new RuntimeException("API Error " + response.statusCode() + ": " + error);
//        }
//    }
//}



package com.Quantam.Text_Generation_By_AI.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.*;
import java.util.Base64;

@Service
public class ImageGenerationService {

    @Value("${huggingface.api.key}")
    private String apiKey;

    @Value("${huggingface.api.url}")
    private String apiUrl;

    public String generateImage(String prompt) throws Exception {

        System.out.println("=================================");
        System.out.println(">>> URL  : " + apiUrl);
        System.out.println(">>> KEY  : " + apiKey.substring(0, 8) + "...");
        System.out.println("=================================");

        // escape quotes
        String escapedPrompt = prompt.replace("\"", "\\\"");
        String requestBody = "{\"inputs\": \"" + escapedPrompt + "\"}";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<byte[]> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofByteArray()
        );

        System.out.println(">>> STATUS: " + response.statusCode());

        if (response.statusCode() == 200) {
            System.out.println(">>> SUCCESS!");
            return Base64.getEncoder().encodeToString(response.body());
        } else {
            String error = new String(response.body());
            System.out.println(">>> ERROR: " + error);
            throw new RuntimeException("API Error " + response.statusCode() + ": " + error);
        }
    }
}