package com.example.appjam.controller.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SubwayInfo {
    private String subwayName;
    private String startGate;
    private String endGate;
    private double lon;
    private double lat;
    private List<Integer> congestion;

    public SubwayInfo(String subwayName, String startGate, String endGate, double lon, double lat, List<Integer> congestion) {
        this.subwayName = subwayName;
        this.startGate = startGate;
        this.endGate = endGate;
        this.lon = lon;
        this.lat = lat;
        this.congestion = congestion;
    }
}
