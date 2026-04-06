package com.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping
    public String getUsers(){
        return "Hello this is test spring boot app!!";
    }

    @GetMapping("/getAllUsers/{userName}")
    public ResponseEntity<?> getAllUsers(@PathVariable String userName){
        if (userName.equalsIgnoreCase("Alok")) {
            return ResponseEntity.ok("This API endpoint is to get all users....");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestParam String name, @RequestParam Integer age){
        return ResponseEntity.ok("UserName: " + name + " Age: " + age + " created");
    }

}
