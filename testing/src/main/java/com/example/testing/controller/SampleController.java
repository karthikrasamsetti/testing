package com.example.testing.controller;

import com.example.testing.demo.UserService;
import com.example.testing.dto.ListUserResponse;
import com.example.testing.dto.PatchRequest;
import com.example.testing.dto.UserDto;
import com.example.testing.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public  class SampleController {

    private final UserService service;
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
    @PostMapping("/user")
    public ResponseEntity<UserResponse> insertUser(@RequestBody UserDto user){
        return ResponseEntity.ok(service.insert(user));
    }

    @GetMapping("/user")
    public ResponseEntity<ListUserResponse>getUser() {
        return  ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable int id){
        return ResponseEntity.ok(service.getById(id));
    }
    @PutMapping("/user")
    public  ResponseEntity<UserResponse> updateUser(@RequestBody UserDto user){
        return ResponseEntity.ok(service.update(user));
    }
    @PatchMapping("user/{id}")
    public ResponseEntity<UserResponse> patchUser(@PathVariable int id, @RequestBody @NonNull PatchRequest patchRequest){
        return ResponseEntity.ok(service.patch(id, patchRequest.map()));
    }
}