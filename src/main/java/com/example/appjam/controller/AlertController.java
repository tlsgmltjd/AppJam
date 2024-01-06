package com.example.appjam.controller;

import com.example.appjam.controller.dto.request.AlertRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AlertController {
    @PostMapping("/alert")
    public ResponseEntity<AlertRequest> alert(@RequestBody AlertRequest request) {
        return ResponseEntity.ok(request);
    }
}
