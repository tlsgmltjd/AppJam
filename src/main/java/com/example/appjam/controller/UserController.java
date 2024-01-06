package com.example.appjam.controller;

import com.example.appjam.controller.dto.LoginRequest;
import com.example.appjam.controller.dto.UserInfoResponse;
import com.example.appjam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(LoginRequest request) {
        userService.login(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<UserInfoResponse> userInfo(@RequestParam String token) {
        return ResponseEntity.ok(userService.userInfo(token));
    }
}
