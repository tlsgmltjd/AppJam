package com.example.appjam.service;


import com.example.appjam.client.FeignClient;
import com.example.appjam.controller.dto.request.FeignRequest;
import com.example.appjam.controller.dto.request.MapRequest;
import com.example.appjam.controller.dto.response.MapResponse;
import com.example.appjam.controller.dto.response.TransitResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeignService {

    private final FeignClient feignClient;

    public TransitResponseDTO getContributor(MapRequest request) {
        TransitResponseDTO contributor = feignClient.getContributor("e8wHh2tya84M88aReEpXCa5XTQf3xgo01aZG39k5", FeignRequest.builder()
                        .startX(request.getStartX())
                        .startY(request.getStartY())
                        .endX(request.getEndX())
                        .endY(request.getEndY())
                .build());
        return contributor;
    }
}