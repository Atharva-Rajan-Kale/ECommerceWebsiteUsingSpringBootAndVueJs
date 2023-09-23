package com.example.ecommercewebsite.controller;

import com.example.ecommercewebsite.dto.ResponseDto;
import com.example.ecommercewebsite.dto.user.SignInDto;
import com.example.ecommercewebsite.dto.user.SignInResponseDto;
import com.example.ecommercewebsite.dto.user.SignUpDto;
import com.example.ecommercewebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignUpDto signUpDto){
        return userService.signup(signUpDto);
    }

    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto){
        return userService.signin(signInDto);
    }
}
