package com.example.appjam.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class AlertRequest {
    private String position;
    private String lon;
    private String lat;
}
