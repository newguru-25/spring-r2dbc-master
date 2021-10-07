package com.reactor.demo.service;

import com.reactor.demo.dto.TimeMetricDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface TimeMetricService {

    Flux<TimeMetricDto> getAll();
    Mono<TimeMetricDto> save(TimeMetricDto timeMetricDto);
}
