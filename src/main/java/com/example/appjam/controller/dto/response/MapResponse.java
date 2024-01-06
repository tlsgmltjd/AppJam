package com.example.appjam.controller.dto.response;

import lombok.*;

import java.util.List;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder @Data
public class MapResponse {
    private Integer id;
    private Integer fee;
    private Integer time;
    private List<Subway> subway;
}
