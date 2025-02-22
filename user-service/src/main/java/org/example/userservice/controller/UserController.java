package org.example.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;@RestController
public class UserController {

    private final RestTemplate restTemplate;

    @Autowired
    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/user")
    public String getUser(@RequestParam String username) {
        return username;
    }

    @GetMapping("/ask")
    public String askQuestion(@RequestParam String question) {
        String aiServiceUrl = "http://localhost:8084/ai/ask?question=" + question;  // 传递问题作为查询参数

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                aiServiceUrl, HttpMethod.GET, entity, String.class
        );
        return response.getBody();
    }
}
