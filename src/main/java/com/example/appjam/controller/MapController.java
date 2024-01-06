package com.example.appjam.controller;

import com.example.appjam.controller.dto.request.MapRequest;
import com.example.appjam.controller.dto.response.MapResponse;
import com.example.appjam.controller.dto.response.TransitResponseDTO;
import com.example.appjam.service.FeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MapController {
    private final FeignService feignService;

    @PostMapping(value = "/map")
    public TransitResponseDTO getGitHubContributors(@RequestBody MapRequest request){
        return feignService.getContributor(request);
    }
}
