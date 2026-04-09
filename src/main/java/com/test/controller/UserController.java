package com.test.controller;

import com.test.dto.APIResponseDTO;
import com.test.dto.EmployeeDTO;
import com.test.dto.UserDTO;
import com.test.entity.UserEntity;
import com.test.service.RestApiCall;
import com.test.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final RestApiCall restApiCall;
    @GetMapping("/getAllEmployees")
    public ResponseEntity<APIResponseDTO<List<EmployeeDTO>>> getAllEmployees(){
        return ResponseEntity.ok(restApiCall.getAllEmployees());
    }

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
    @GetMapping("/getusers")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/getUserByName/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name){
        return ResponseEntity.ok(userService.getUserByName(name));
    }

   /* @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
    }*/
    @DeleteMapping("/deleteUserByNamr/{name}")
    public ResponseEntity<?> deleteUserByName(@PathVariable String name){
        return ResponseEntity.ok(userService.deleteUserByName(name));
    }
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PutMapping("/updateUserById/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable String id, @RequestBody UserDTO userDTO ){
        return ResponseEntity.ok(userService.updateUserById(id, userDTO));
    }
    @GetMapping("/getUerByAge/{age}")
    public ResponseEntity<List<UserDTO>> getUserByAge(@PathVariable int age){
        return ResponseEntity.ok(userService.getUserByAge(age));
    }
}
