package com.example.appjam.service;


import com.example.appjam.client.FeignClient;
import com.example.appjam.client.SubwayCongestionFeignClient;
import com.example.appjam.controller.dto.request.FeignRequest;
import com.example.appjam.controller.dto.request.MapRequest;
import com.example.appjam.controller.dto.response.MapResponse;
import com.example.appjam.controller.dto.response.Subway;
import com.example.appjam.controller.dto.response.SubwayCongestionResponseDTO;
import com.example.appjam.controller.dto.response.TransitResponseDTO;
import com.example.appjam.repository.SubwayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeignService {

    private Integer id = 1;
    int count = 0;

    private final FeignClient feignClient;
    private final SubwayCongestionFeignClient subwayCongestionFeignClient;
    private final SubwayRepository subwayRepository;

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

        List<String> cods = new ArrayList<>();

         mapResponses.stream()
                .map(mapResponse -> mapResponse.getSubway()
                        .stream()
                        .map(subway -> cods.add(subwayRepository.findBySubwayLineAndStationName(subway.getSubwayName().charAt(3) + "호선", subway.getStartGate() + "역")
                                .orElse(new com.example.appjam.domain.Subway(0L, "0", "0", "0")).getStationCode())).toList()).toList();

         List<SubwayCongestionResponseDTO> subwayCongestionResponseDTOS = cods.stream()
                        .filter(code -> !code.equals("0"))
                         .map(code -> subwayCongestionFeignClient.getSubwayCongestion(code, "N2QzY827oC1RFR7xspN166M3miWS6RY5aWQMUZzE"))
                 .toList();

        List<List<Integer>> congestion = new ArrayList<>();

        subwayCongestionResponseDTOS.stream()
                .map(subwayCongestionResponseDTO -> subwayCongestionResponseDTO.getContents().getStat().get(0).getData().stream()
                        .filter(s -> s.getMm().equals("30"))
                        .map(s -> {
                            congestion.add(s.getCongestionCars());
                            return s;
                        })
                        .toList())
                .toList();


        mapResponses.stream()
                .map(mapResponse -> mapResponse.getSubway()
                        .stream()
                        .map(subway -> {
                                subway.setCongestion(congestion.get(congestion.size() <= count ? 0 : count));
                                count = count + 1;
                                return subway;
                        })
                        .toList())
                .toList();

        count = 0;

        return mapResponses;
    }
}