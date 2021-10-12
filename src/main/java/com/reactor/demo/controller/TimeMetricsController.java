package com.reactor.demo.controller;


import com.reactor.demo.dto.TimeMetricDayDto;
import com.reactor.demo.dto.TimeMetricHourDto;
import com.reactor.demo.dto.TimeMetricRequestDto;
import com.reactor.demo.service.TimeMetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/timemetric")
@RequiredArgsConstructor
public class TimeMetricsController {

    private final TimeMetricService timeMetricService;

    @GetMapping("/rangohora")
    public Flux<TimeMetricHourDto> getMetricByHour(@RequestParam String fecha) {
        return timeMetricService.getStadisticMetricByHours(fecha);
    }

    @GetMapping("/rangodia")
    public Mono<TimeMetricDayDto> getMetricByDay(@RequestParam String fecha) {
        return timeMetricService.getMetricByDay(fecha);
    }

    @PostMapping
    public Mono<Void> save(@RequestBody TimeMetricRequestDto timeMetricRequestDto) {
        return timeMetricService.save(timeMetricRequestDto);
    }

}
