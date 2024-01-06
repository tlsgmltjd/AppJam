package com.example.appjam.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class MapRequest {
    private String token;
    private String startX;
    private String startY;
    private String endX;
    private String endY;
}
