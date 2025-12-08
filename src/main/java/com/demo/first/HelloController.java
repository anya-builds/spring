package com.demo.first;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World!!";
    }
    @GetMapping("/user")
    public  User getUser(){
        User user=new User(1,"John Doe","john@example.com");
        return user;
    }
}
