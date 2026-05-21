package com.Quantam.Text_Generation_By_AI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Quantam.Text_Generation_By_AI.DTO.PromptRequest;
import com.Quantam.Text_Generation_By_AI.service.AiService;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:5173")
public class TestController {

    @Autowired
    private AiService service;

    @PostMapping("/ask")
    public String ask(@RequestBody PromptRequest req) throws Exception{

        return service.getAnswer(req.getPrompt());
    }
}