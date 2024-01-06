package com.example.appjam.controller;

import com.example.appjam.controller.dto.request.MapRequest;
import com.example.appjam.controller.dto.response.Journey;
import com.example.appjam.controller.dto.response.MapResponse;
import com.example.appjam.controller.dto.response.SubwayInfo;
import com.example.appjam.controller.dto.response.TransitResponseDTO;
import com.example.appjam.fb.FirebaseInitializer;
import com.example.appjam.fb.FirebaseReader;
import com.example.appjam.service.FeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MapController {
    private final FeignService feignService;

    @PostMapping(value = "/map")
    public List<MapResponse> getGitHubContributors(@RequestBody MapRequest request){
        return feignService.getContributor(request);
    }

    @GetMapping("/test")
    public ResponseEntity<List<Journey>> test() {
        List<Journey> journeys = Arrays.asList(
                new Journey(1, 1700, 4178, Arrays.asList(
                        new SubwayInfo("수도권6호선", "상수", "약수", 126.92238055555555, 37.547761111111114, Arrays.asList(12, 10, 11, 9, 10, 20, 14, 10)),
                        new SubwayInfo("수도권3호선", "약수", "수서", 127.01099444444445, 37.55435833333333, Arrays.asList(17, 20, 15, 14, 16, 14, 19, 17, 15, 13))
                )),
                new Journey(2, 1700, 3906, Arrays.asList(
                        new SubwayInfo("수도권2호선", "홍대입구", "왕십리", 126.92349722222222, 37.55663055555556, Arrays.asList(5, 7, 7, 7, 6, 5, 4, 3, 3, 2)),
                        new SubwayInfo("수인분당선", "왕십리", "수서", 127.03876944444444, 37.56118611111111, Arrays.asList(5, 7, 7, 7, 6, 5, 4, 3, 3, 2))
                )),
                new Journey(3, 1700, 4364, Arrays.asList(
                        new SubwayInfo("수도권2호선", "홍대입구", "선릉", 126.92349722222222, 37.55663055555556, Arrays.asList(5, 7, 7, 7, 6, 5, 4, 3, 3, 2)),
                        new SubwayInfo("수인분당선", "선릉", "수서", 127.04866111111112, 37.50525277777778, Arrays.asList(16, 25, 21, 25, 25, 20, 21, 20, 17, 11))
                )),
                new Journey(4, 1700, 4187, Arrays.asList(
                        new SubwayInfo("수도권2호선", "홍대입구", "을지로3가", 126.92349722222222, 37.55663055555556, Arrays.asList(5, 7, 7, 7, 6, 5, 4, 3, 3, 2)),
                        new SubwayInfo("수도권3호선", "을지로3가", "수서", 126.99256944444444, 37.56680277777778, Arrays.asList(12, 17, 21, 24, 19, 21, 19, 16, 16, 13))
                )),
                new Journey(5, 1700, 4366, Arrays.asList(
                        new SubwayInfo("수도권2호선", "홍대입구", "교대", 126.92349722222222, 37.55663055555556, Arrays.asList(5, 7, 7, 7, 6, 5, 4, 3, 3, 2)),
                        new SubwayInfo("수도권3호선", "교대", "수서", 127.01389444444445, 37.49268333333333, Arrays.asList(20, 29, 16, 17, 44, 24))
                )),
                new Journey(6, 1700, 3635, Arrays.asList(
                        new SubwayInfo("수도권2호선", "홍대입구", "당산", 126.92349722222222, 37.55663055555556, Arrays.asList(12, 10, 11, 9, 10, 20, 14, 10)),
                        new SubwayInfo("수도권9호선(급행)", "당산", "선정릉", 126.90265277777777, 37.533525, Arrays.asList(12, 10, 11, 9, 10, 20, 14, 10)),
                        new SubwayInfo("수인분당선", "선정릉", "수서", 127.04367777777777, 37.510825, Arrays.asList(12, 10, 11, 9, 10, 20, 14, 10))
                ))
        );

        return ResponseEntity.ok(journeys);
    }
}
