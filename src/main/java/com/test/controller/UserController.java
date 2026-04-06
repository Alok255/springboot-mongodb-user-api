package com.test.controller;

import com.test.dto.UserDTO;
import com.test.entity.UserEntity;
import com.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

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
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
         UserEntity createUser = userService.createUser(userDTO);
         return ResponseEntity.ok("User created: " + createUser);
    }

   /* @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
    }*/

}
