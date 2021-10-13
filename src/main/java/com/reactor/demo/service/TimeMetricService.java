package com.reactor.demo.service;

import com.reactor.demo.dto.TimeMetricDayDto;
import com.reactor.demo.dto.TimeMetricHourDto;
import com.reactor.demo.dto.TimeMetricRequestDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface TimeMetricService {

    Flux<TimeMetricHourDto> getMetricByHour(String fecha);

    Mono<TimeMetricDayDto> getMetricByDay(String fecha);

    Flux<TimeMetricHourDto> getStadisticMetricByHours(String fecha);

    Mono<Void> save(TimeMetricRequestDto timeMetricStoreDto);
}
