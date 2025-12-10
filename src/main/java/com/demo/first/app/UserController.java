package com.demo.first.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private Map<Integer, User> userDb = new HashMap<>();

    @PostMapping
    public ResponseEntity<User>  createUser(@RequestBody User user){
        System.out.println(user.getEmail());
        userDb.putIfAbsent(user.getId(), user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        if(!userDb.containsKey(user.getId()))
            return ResponseEntity.notFound().build();
            userDb.put(user.getId(), user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        if (userDb.containsKey(id))
            return "User not found!";
        userDb.remove(id);
        return "User Deleted!";
    }

    @GetMapping
    public List<User> getUsers(){
        return new ArrayList<>(userDb.values());
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(){
        return ResponseEntity.ok("Order for User: ");
    }
}
