package com.example.appjam.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SubwayCongestionResponseDTO {

    private Status status;
    private Contents contents;

    @lombok.Data
    public static final class Status {
        private String code;
        private String message;
        private int totalCount;
    }

    @lombok.Data
    public static class Contents {
        private String subwayLine;
        private String stationName;
        private String stationCode;
        private List<Stat> stat;
        private String statStartDate;
        private String statEndDate;
    }

    @lombok.Data
    public static class Stat {
        private String startStationCode;
        private String startStationName;
        private String endStationCode;
        private String endStationName;
        private String prevStationCode;
        private String prevStationName;
        private int updnLine;
        private int directAt;
        private List<Data> data;
    }

    @lombok.Data
    public static class Data {
        private String dow;
        private String hh;
        private String mm;
        @JsonProperty("congestionCar")
        private List<Integer> congestionCars;
    }
}
