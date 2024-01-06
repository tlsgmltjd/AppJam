package com.example.appjam.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class FeignRequest {
    private String startX;
    private String startY;
    private String endX;
    private String endY;
    private Integer lang = 0;
    private String format = "json";
    private Integer count = 10;
}
