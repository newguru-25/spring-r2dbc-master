package com.reactor.demo.controller;


import com.reactor.demo.dto.TimeMetricDayDto;
import com.reactor.demo.dto.TimeMetricHourDto;
import com.reactor.demo.dto.TimeMetricRequestDto;
import com.reactor.demo.service.TimeMetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/timemetric")
@RequiredArgsConstructor
public class TimeMetricsController {

    private final TimeMetricService timeMetricService;

    @GetMapping("/rangohora")
    public Mono<List<TimeMetricHourDto>> getMetricByHour(@RequestParam String fechaIni, @RequestParam String fechaFin) {
        return timeMetricService.getMetricByHour(fechaIni,fechaFin);
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
