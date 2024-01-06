package com.example.appjam.controller.dto.response;

import lombok.*;

import java.util.List;

@Data @NoArgsConstructor
public class Journey {
    private int id;
    private int fee;
    private int time;
    private List<SubwayInfo> subway;

    public Journey(int id, int fee, int time, List<SubwayInfo> subway) {
        this.id = id;
        this.fee = fee;
        this.time = time;
        this.subway = subway;
    }
}
