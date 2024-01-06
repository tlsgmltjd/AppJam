package com.example.appjam.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class MapResponse {
    private Integer id;
    private Integer fee;
    private Integer time;
    private List<Subway> subway;
}
