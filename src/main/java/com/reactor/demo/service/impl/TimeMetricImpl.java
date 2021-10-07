package com.reactor.demo.service.impl;

import com.reactor.demo.cast.TimeMetricCast;
import com.reactor.demo.dto.TimeMetricDto;
import com.reactor.demo.entity.TimeMetricEntity;
import com.reactor.demo.repository.TimeMetricRepository;
import com.reactor.demo.service.TimeMetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class TimeMetricImpl implements TimeMetricService {

    private final TimeMetricRepository timeMetricRepository;

    @Override
    public Flux<TimeMetricDto> getAll() {
        return timeMetricRepository.findAll().map(TimeMetricCast::toModel);
    }

    @Override
    public Mono<TimeMetricDto> save(TimeMetricDto timeMetricDto) {
        return TimeMetricCast.toModelMono(timeMetricRepository.save(TimeMetricCast.toEntity(timeMetricDto)));
    }

}
