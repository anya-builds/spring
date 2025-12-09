package com.demo.first.app;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private Map<Integer, User> userDb = new HashMap<>();

    @PostMapping
    public String  createUser(@RequestBody User user){
        System.out.println(user.getEmail());
        userDb.putIfAbsent(user.getId(), user);
        return "User Created!!";
    }

    @PutMapping
    public void updateUser(@RequestBody User user){
        userDb.put(user.getId(), user);
    }
}
