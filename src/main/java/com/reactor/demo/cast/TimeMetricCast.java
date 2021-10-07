package com.reactor.demo.cast;

import com.reactor.demo.entity.TimeMetricEntity;
import com.reactor.demo.dto.TimeMetricDto;
import reactor.core.publisher.Mono;

public class TimeMetricCast {
    public static TimeMetricDto toModel(TimeMetricEntity entity) {
        TimeMetricDto model = new TimeMetricDto();
        model.setId(entity.getId());
        model.setTemperature(entity.getTemperature());
        model.setDatetime(entity.getDatetime());
        return model;
    }

    public static TimeMetricEntity toEntity(TimeMetricDto model) {
        TimeMetricEntity entity= new TimeMetricEntity();
        entity.setId(model.getId());
        entity.setTemperature(model.getTemperature());
        entity.setDatetime(model.getDatetime());
        return entity;
    }

    public static Mono<TimeMetricDto> toModelMono(Mono<TimeMetricEntity> entity) {
        return entity.flatMap(timeMetricEmtity -> {
            final TimeMetricDto timeMetricDto = new TimeMetricDto();
            timeMetricDto.setId(timeMetricEmtity.getId());
            timeMetricDto.setTemperature(timeMetricEmtity.getTemperature());
            timeMetricDto.setDatetime(timeMetricEmtity.getDatetime());
            return Mono.just(timeMetricDto);
        });
    }


}
