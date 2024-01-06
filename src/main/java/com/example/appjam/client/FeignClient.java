package com.example.appjam.client;

import com.example.appjam.controller.dto.request.FeignRequest;
import com.example.appjam.controller.dto.request.MapRequest;
import com.example.appjam.controller.dto.response.MapResponse;
import com.example.appjam.controller.dto.response.TransitResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.cloud.openfeign.FeignClient(name = "feign", url = "https://apis.openapi.sk.com/transit")
public interface FeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/routes")
    TransitResponseDTO getContributor(
            @RequestHeader("appKey") String appKey,
            @RequestBody FeignRequest request
    );
}