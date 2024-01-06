package com.example.appjam.controller;

import com.example.appjam.controller.dto.request.LoginRequest;
import com.example.appjam.controller.dto.response.UserInfoResponse;
import com.example.appjam.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/auth/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request) {
        userService.login(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/auth/profile")
    public ResponseEntity<UserInfoResponse> userInfo(@RequestParam("token") String token) {
        return ResponseEntity.ok(userService.userInfo(token));
    }

    @PostMapping("/point")
    public ResponseEntity<Void> point(@RequestParam("token") String token) {
        userService.point(token);
        return ResponseEntity.noContent().build();
    }
}
