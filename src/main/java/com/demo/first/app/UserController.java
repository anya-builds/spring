package com.demo.first.app;

import jakarta.websocket.OnClose;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService = new UserService();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User>  createUser(@RequestBody User user){
       User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User updated = userService.updateUser(user);
        if(updated== null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        boolean isDeleted = userService.deleteUser(id);
        if(!isDeleted)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.noContent().build();


    }

//    @GetMapping("/users","/user/{id}")


    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(
            @PathVariable(value = "userId", required = false) int id){
        User user = userService.getUserById(id);
        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public ResponseEntity<User> getUserOrder(
            @PathVariable(value = "userId", required = false) int id){
        User user = userService.getUserById(id);
        if(user==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(user);

    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(
            @RequestParam(required = false, defaultValue = "alice") String name,
            @RequestParam(required = false, defaultValue = "email") String email){


        return ResponseEntity.ok(userService.searchUsers(name, email));
    }

    @GetMapping("/info/{id}")
    public String getInfo(
            @PathVariable int id,
            @RequestParam String name,
            @RequestHeader("user-Agent") String userAgent){
        return "User Agent: "+userAgent
                +" : "+id
                +" : "+name;
    }
    //exception handling method
    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(
            IllegalArgumentException exception
    ){
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status",HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Bad Request");
        errorResponse.put("message", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
