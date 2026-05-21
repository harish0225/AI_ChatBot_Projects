package com.Quantam.Text_Generation_By_AI.service;
//
//
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class AiService {
//
//    @Value("${Gemini.API.Key}")
//    private String apiKey;
//
//    public String getAnswer(String prompt){
//
//    	String url = "https://api.groq.com/openai/v1/chat/completions";
//
//    	String requestBody = """
//    			{
//    			  "model": "llama-3.3-70b-versatile",
//    			  "messages": [
//    			    {
//    			      "role": "user",
//    			      "content": "%s"
//    			    }
//    			  ]
//    			}
//    			""".formatted(prompt);
//
//        HttpHeaders headers =
//                new HttpHeaders();
//
//        headers.setContentType(
//                MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(apiKey);
//
//        HttpEntity<String> entity =
//                new HttpEntity<>(
//                        requestBody,
//                        headers
//                );
//
//        RestTemplate rt =
//                new RestTemplate();
//
//        ResponseEntity<String> response =
//                rt.exchange(
//                        url,
//                        HttpMethod.POST,
//                        entity,
//                        String.class
//                );
//
//        return response.getBody();
//    }
//}






import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//import tools.jackson.databind.JsonNode;
//import tools.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class AiService {

    @Value("${groq.text.api.key}")
    private String apiKey;

    public String getAnswer(String prompt) throws Exception {

//    	String url =
//    			"https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key="
//    			+ apiKey;

    	String url = "https://api.groq.com/openai/v1/chat/completions";

//        String requestBody = """
//        {
//          "contents": [
//            {
//              "parts": [
//                {
//                  "text": "%s"
//                }
//              ]
//            }
//          ]
//        }
//        """.formatted(prompt);

    	String requestBody = """
    			{
    			  "model": "llama-3.3-70b-versatile",
    			  "messages": [
    			    {
    			      "role": "user",
    			      "content": "%s"
    			    }
    			  ]
    			}
    			""".formatted(prompt);

        HttpHeaders headers =
                new HttpHeaders();

        headers.setContentType(
                MediaType.APPLICATION_JSON);

        headers.setBearerAuth(apiKey);

        HttpEntity<String> entity =
                new HttpEntity<>(
                        requestBody,
                        headers
                );

        RestTemplate rt =
                new RestTemplate();

        ResponseEntity<String> response =
                rt.exchange(
                        url,
                        HttpMethod.POST,
                        entity,
                        String.class
                );

        ObjectMapper mapper =
                new ObjectMapper();

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