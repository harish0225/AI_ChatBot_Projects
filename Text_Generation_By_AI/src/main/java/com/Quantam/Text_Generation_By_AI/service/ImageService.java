package com.Quantam.Text_Generation_By_AI.service;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ImageService {

    @Value("${groq.image.api.key}")
    private String apiKey;

    public String getImageInfo(MultipartFile image) throws Exception {

        String url =
                "https://api.groq.com/openai/v1/chat/completions";

        // Convert image to Base64
        String base64Image =
                Base64.getEncoder()
                        .encodeToString(image.getBytes());

        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Request Body
        Map<String, Object> imageUrl =
                Map.of(
                        "url",
                        "data:image/jpeg;base64," + base64Image
                );

        Map<String, Object> imageContent =
                Map.of(
                        "type", "image_url",
                        "image_url", imageUrl
                );

        Map<String, Object> textContent =
                Map.of(
                        "type", "text",
                        "text", "Describe this image"
                );

        Map<String, Object> message =
                Map.of(
                        "role", "user",
                        "content", List.of(textContent, imageContent)
                );

        Map<String, Object> body =
                Map.of(
                        "model",
                        "meta-llama/llama-4-scout-17b-16e-instruct",

                        "messages",
                        List.of(message)
                );

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(body, headers);

        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> response =
                rt.exchange(
                        url,
                        HttpMethod.POST,
                        entity,
                        String.class
                );

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root =
                mapper.readTree(response.getBody());

        String answer =
                root.get("choices")
                        .get(0)
                        .get("message")
                        .get("content")
                        .asText();

        return answer;
    }
}