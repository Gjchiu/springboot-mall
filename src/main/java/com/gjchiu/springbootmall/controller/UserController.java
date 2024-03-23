package com.gjchiu.springbootmall.controller;

import com.gjchiu.springbootmall.dto.UserLoginRequest;
import com.gjchiu.springbootmall.dto.UserRequest;
import com.gjchiu.springbootmall.model.User;
import com.gjchiu.springbootmall.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRequest userRequest){
        Integer userId = userService.register(userRequest);
        User user = userService.getUserById(userId);

        if(user != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody UserLoginRequest userRequest){
        User user = userService.login(userRequest);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
