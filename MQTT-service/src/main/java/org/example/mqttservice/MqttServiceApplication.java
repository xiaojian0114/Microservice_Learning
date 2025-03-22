package org.example.mqttservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  // 启用 Nacos 服务注册
public class MqttServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqttServiceApplication.class, args);
    }
}
