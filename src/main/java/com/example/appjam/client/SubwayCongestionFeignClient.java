package com.example.appjam.client;

import com.example.appjam.controller.dto.response.SubwayCongestionResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.cloud.openfeign.FeignClient(name = "feign2", url = "https://apis.openapi.sk.com/puzzle/subway/congestion/stat/car/stations")
public interface SubwayCongestionFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{stationCode}?hh=08")
    SubwayCongestionResponseDTO getSubwayCongestion(
            @PathVariable("stationCode") String stationCode,
            @RequestParam("appKey") String appKey
    );
}

