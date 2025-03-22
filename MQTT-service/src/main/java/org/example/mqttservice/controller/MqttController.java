package org.example.mqttservice.controller;


import org.example.mqttservice.service.MqttMessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mqtt")
public class MqttController {

    private final MqttMessageService mqttMessageService;

    public MqttController(MqttMessageService mqttMessageService) {
        this.mqttMessageService = mqttMessageService;
    }

    @PostMapping("/send")
    public String sendMqttMessage(@RequestParam String message) {
        mqttMessageService.sendMessage(message);
        return "Message send: " + message;
    }
}
