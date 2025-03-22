package org.example.helloservice.controller;

import jakarta.annotation.Resource;
import org.example.helloservice.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Resource
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello(){
        return "hello from " + helloService.getName();
    }

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam String name){
        return helloService.sayHello(name);
    }
}
