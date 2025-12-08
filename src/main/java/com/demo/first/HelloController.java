package com.demo.first;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    public String sayHello(){
        return "Hello World!!";
    }
}
