package com.example.appjam.controller;

import com.example.appjam.controller.dto.request.AlertRequest;
import com.example.appjam.service.FirebaseCloudMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AlertController {

    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @PostMapping("/alert")
    public ResponseEntity<AlertRequest> alert(@RequestBody AlertRequest request) throws IOException {

    firebaseCloudMessageService.sendMessageTo(
            "fxyFoyH2TFe5Inlp9AOZ0K:APA91bGR7IjT5QE1PyJd27sZSigbf8pu5jqNVB8knmtkQsTu2-vpxJJ_csGkFswRDgLjEUp5h8srAS7MLhpKGIcDBdNAD4h0fVD62NKwkKtzTax5s2ImockBbNLS6iLFDf_by8KPLQ9Q",
            "비켜주세요!!",
            request.getPosition() + "에서 노약자분의 하차를 도와주세요!"
    );

        return ResponseEntity.ok(request);
    }
}
