package org.example.orderservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/order")

    public String createOrder(@RequestParam String username ,  Integer productId ,Integer orderId) {
        String userServiceUrl = "http://localhost:8081/user?username=" + username;
        String userInfo = restTemplate.getForObject(userServiceUrl, String.class);

        String ProductServiceUrl = "http://localhost:8083/product?productId=" + productId;
        String ProductInfo = restTemplate.getForObject(ProductServiceUrl, String.class);


        return "订单创建者：" + userInfo + ",订单编号:" + ProductInfo + "，下单人编号：" + orderId;


}
}
