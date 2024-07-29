package com.rest.api.controller;// src/main/java/com/auth0/example/web/APIController.java

import com.rest.api.dto.AuthRequestDTO;
import com.rest.api.dto.AuthTokenResponse;
import com.rest.api.dto.UserDTO;
import com.rest.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthTokenResponse> loginUser(@RequestBody AuthRequestDTO authRequestDTO) {
        AuthTokenResponse authTokenResponse = userService.loginUser(authRequestDTO);
        return ResponseEntity.ok(authTokenResponse);
    }

}