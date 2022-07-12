package com.example.cryptoinfo.controller;

import com.example.cryptoinfo.model.dto.request.SignUpRequestDto;
import com.example.cryptoinfo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<?> registerForNotification(@RequestBody SignUpRequestDto signUpRequestDto){
        userService.registerUser(signUpRequestDto.getUsername(),signUpRequestDto.getSymbol());
        return ResponseEntity.ok().body("You're successfully signed for notifications");
    }
}
