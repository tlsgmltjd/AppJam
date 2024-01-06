package com.example.appjam.repository;

import com.example.appjam.domain.Subway;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubwayRepository extends JpaRepository<Subway, Long> {
    Optional<Subway> findBySubwayLineAndStationName(String subwayLine, String stationName);
}
