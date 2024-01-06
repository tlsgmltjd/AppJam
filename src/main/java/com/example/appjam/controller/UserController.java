package com.example.appjam.controller;

import com.example.appjam.controller.dto.request.LoginRequest;
import com.example.appjam.controller.dto.response.UserInfoResponse;
import com.example.appjam.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request) {
        userService.login(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<UserInfoResponse> userInfo(@RequestParam("token") String token) {
        return ResponseEntity.ok(userService.userInfo(token));
    }
}
