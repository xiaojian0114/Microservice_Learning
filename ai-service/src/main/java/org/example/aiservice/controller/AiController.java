package org.example.aiservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;@RestController
@RequestMapping("/ai")
public class AiController {

    private final RestTemplate restTemplate;

    @Autowired
    public AiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestParam String question) {
        String apiUrl = "https://dashscope.aliyuncs.com/compatible-mode/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer sk-93bfe4546c374e23987764507288eb94"); // 添加Bearer前缀
        headers.set("Content-Type", "application/json");

        // 使用JSON库构建请求体
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode requestBody = mapper.createObjectNode();
        requestBody.put("model", "qwen-plus");
        ArrayNode messages = mapper.createArrayNode();
        ObjectNode message = mapper.createObjectNode();
        message.put("role", "user");
        message.put("content", question);  // 使用传入的参数作为问题
        messages.add(message);
        requestBody.set("messages", messages);

        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);

        // 处理响应，提取问题和答案
        String responseBody = response.getBody();
        if (responseBody != null) {
            try {
                ObjectNode responseJson = (ObjectNode) mapper.readTree(responseBody);
                String answer = responseJson.at("/choices/0/message/content").asText();  // 获取回答内容
                return ResponseEntity.ok("问题: " + question + "\n答案: " + answer);  // 返回问题和答案
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("解析失败");
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("响应为空");
    }
}
