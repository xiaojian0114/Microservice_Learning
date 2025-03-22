package org.example.helloservice.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String getName(){
        return "Hello World";
    }

    public String sayHello(String name){
        return "hello " + name;
    }
}
