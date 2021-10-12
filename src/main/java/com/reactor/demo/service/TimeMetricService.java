package com.reactor.demo.service;

import com.reactor.demo.dto.TimeMetricDayDto;
import com.reactor.demo.dto.TimeMetricHourDto;
import com.reactor.demo.dto.TimeMetricRequestDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public interface TimeMetricService {

    Mono<List<TimeMetricHourDto>> getMetricByHour(String fechaIni, String fechaFin);
    Mono<TimeMetricDayDto> getMetricByDay(String fecha);
    Mono<Void> save(TimeMetricRequestDto timeMetricStoreDto);
}
