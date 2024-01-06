package com.example.appjam.service;


import com.example.appjam.client.FeignClient;
import com.example.appjam.controller.dto.request.FeignRequest;
import com.example.appjam.controller.dto.request.MapRequest;
import com.example.appjam.controller.dto.response.MapResponse;
import com.example.appjam.controller.dto.response.Subway;
import com.example.appjam.controller.dto.response.TransitResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeignService {

    private Integer id = 1;

    private final FeignClient feignClient;

    public List<MapResponse> getContributor(MapRequest request) {
        TransitResponseDTO contributor = feignClient.getContributor("e8wHh2tya84M88aReEpXCa5XTQf3xgo01aZG39k5", FeignRequest.builder()
                        .startX(request.getStartX())
                        .startY(request.getStartY())
                        .endX(request.getEndX())
                        .endY(request.getEndY())
                .build());

        List<String> allowedModes = Arrays.asList("WALK", "SUBWAY");

        List<TransitResponseDTO.Itinerary> itineraries = contributor.getMetaData().getPlan().getItineraries()
                .stream()
                .filter(itinerary ->
                        itinerary.getLegs().stream().allMatch(leg ->
                                allowedModes.contains(leg.getMode())
                        )
                )
                .toList();


        List<MapResponse> mapResponses = itineraries.stream()
                .map(itinerary -> MapResponse.builder()
                        .id(id++)
                        .fee(itinerary.getFare().getRegular().getTotalFare())
                        .time(itinerary.getTotalTime())
                        .subway(
                                itinerary.getLegs().stream()
                                        .filter(leg -> "SUBWAY".equals(leg.getMode()))
                                        .map(leg -> Subway.builder()
                                                .subwayName(leg.getRoute())
                                                .startGate(leg.getStart().getName())
                                                .endGate(leg.getEnd().getName())
                                                .lon(leg.getStart().getLon())
                                                .lat(leg.getStart().getLat())
                                                .build())
                                        .toList()
                        )
                        .build()
                )
                .toList();

        return mapResponses;
    }
}