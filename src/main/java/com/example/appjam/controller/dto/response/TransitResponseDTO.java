package com.example.appjam.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TransitResponseDTO {

    @JsonProperty("metaData")
    private MetaData metaData;

    @Data
    public static class MetaData {

        @JsonProperty("requestParameters")
        private RequestParameters requestParameters;

        @JsonProperty("plan")
        private Plan plan;
    }

    @Data
    public static class RequestParameters {

        @JsonProperty("busCount")
        private int busCount;

        @JsonProperty("expressbusCount")
        private int expressBusCount;
        // 다른 필드들 추가
    }

    @Data
    public static class Plan {

        @JsonProperty("itineraries")
        private List<Itinerary> itineraries;
    }

    @Data
    public static class Itinerary {

        @JsonProperty("fare")
        private Fare fare;

        @JsonProperty("totalTime")
        private int totalTime;

        @JsonProperty("legs")
        private List<Leg> legs;

        @JsonProperty("totalWalkTime")
        private int totalWalkTime;

        @JsonProperty("transferCount")
        private int transferCount;

        @JsonProperty("totalDistance")
        private int totalDistance;

        @JsonProperty("pathType")
        private int pathType;

        @JsonProperty("totalWalkDistance")
        private int totalWalkDistance;
    }

    @Data
    public static class Fare {

        @JsonProperty("regular")
        private RegularFare regular;
    }

    @Data
    public static class RegularFare {

        @JsonProperty("totalFare")
        private int totalFare;

        @JsonProperty("currency")
        private Currency currency;
    }

    @Data
    public static class Currency {

        @JsonProperty("symbol")
        private String symbol;

        @JsonProperty("currency")
        private String currency;

        @JsonProperty("currencyCode")
        private String currencyCode;
    }

    @Data
    public static class Leg {

        @JsonProperty("mode")
        private String mode;

        @JsonProperty("sectionTime")
        private int sectionTime;

        @JsonProperty("distance")
        private int distance;

        @JsonProperty("start")
        private Location start;

        @JsonProperty("end")
        private Location end;

        @JsonProperty("steps")
        private List<Step> steps;

        private String route;
    }

    @Data
    public static class Location {

        @JsonProperty("name")
        private String name;

        @JsonProperty("lon")
        private double lon;

        @JsonProperty("lat")
        private double lat;
    }

    @Data
    public static class Step {

        @JsonProperty("streetName")
        private String streetName;

        @JsonProperty("distance")
        private int distance;

        @JsonProperty("description")
        private String description;

        @JsonProperty("linestring")
        private String linestring;
    }
}
