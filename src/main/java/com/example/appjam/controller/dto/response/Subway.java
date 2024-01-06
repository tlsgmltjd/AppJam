package com.example.appjam.controller.dto.response;

import lombok.*;

import java.util.List;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder @Data
public class Subway {
    private String subwayName;
    private String startGate;
    private String endGate;
    private Double lon;
    private Double lat;
    private List<Integer> congestion;
}
