package com.example.appjam.controller;

import com.example.appjam.controller.dto.request.AlertRequest;
import com.example.appjam.fb.DistanceCalculator;
import com.example.appjam.fb.FbUser;
import com.example.appjam.fb.FirebaseReader;
import com.example.appjam.service.FirebaseCloudMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class AlertController {

    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @PostMapping("/alert")
    public ResponseEntity<AlertRequest> alert(@RequestBody AlertRequest request) throws IOException {

        CompletableFuture<List<FbUser>> future = FirebaseReader.renderData();

        future.thenAccept(fbUsers -> {
            List<FbUser> targets = new ArrayList<>();

            for (FbUser user : fbUsers) {
                double distance = DistanceCalculator.calculateDistance(
                        Double.parseDouble(request.getLat()),
                        Double.parseDouble(request.getLon()),
                        Double.parseDouble(user.getLat()),
                        Double.parseDouble(user.getLon())
                );

                if (distance <= 10) {
                    targets.add(user);
                }
            }

            for (FbUser target : targets) {
                try {
                    firebaseCloudMessageService.sendMessageTo(
                            target.getToken(),
                            "비켜주세요!!",
                            request.getPosition() + "에서 노약자분의 하차를 도와주세요!"
                    );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(target.getToken());
            }
        }).exceptionally(ex -> {
            // 예외 처리
            ex.printStackTrace();
            return null;
        });

        return ResponseEntity.ok(request);
    }
}
