package org.example.productservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/product")
    public String createProduct( @RequestParam String productId  ) {

       return productId;

    }
}
