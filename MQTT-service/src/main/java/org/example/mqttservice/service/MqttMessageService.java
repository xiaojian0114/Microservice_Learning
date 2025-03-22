package org.example.mqttservice.service;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

@Service
public class MqttMessageService {

    private final MessageChannel mqttOutboundChannel;

    public MqttMessageService(MessageChannel mqttOutboundChannel) {
        this.mqttOutboundChannel = mqttOutboundChannel;
    }

    public void sendMessage(String message) {
        Message<String> mqttMessage = new GenericMessage<>(message);
        mqttOutboundChannel.send(mqttMessage);
    }
}
