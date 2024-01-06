package com.example.appjam.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Subway {
    private String subwayName;
    private String startGate;
    private String endGate;
    private String lon;
    private String lat;
}
