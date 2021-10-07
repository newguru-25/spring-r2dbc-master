package com.reactor.demo.controller;


import com.reactor.demo.dto.TimeMetricDto;
import com.reactor.demo.service.TimeMetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/timemetric")
@RequiredArgsConstructor
public class TimeMetricsController {

    private final TimeMetricService timeMetricService;

    @GetMapping
    public Flux<TimeMetricDto> getAll() {
        return timeMetricService.getAll();
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TimeMetricDto> save(@RequestBody TimeMetricDto timeMetricDto) {
        return timeMetricService.save(timeMetricDto);
    }


}
