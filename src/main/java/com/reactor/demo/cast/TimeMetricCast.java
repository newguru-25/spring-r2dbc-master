package com.reactor.demo.cast;

import com.reactor.demo.dto.TimeMetricRequestDto;
import com.reactor.demo.entity.TimeMetricEntity;
import com.reactor.demo.dto.TimeMetricHourDto;
import reactor.core.publisher.Mono;

public class TimeMetricCast {
    public static TimeMetricHourDto toModel(TimeMetricEntity entity) {
        TimeMetricHourDto model = new TimeMetricHourDto();
//        model.setId(entity.getId());
//        model.setTemperature(entity.getTemperature());
//        model.setDatetime(entity.getDatetime());
        return model;
    }



    public static TimeMetricEntity toEntity(TimeMetricRequestDto model) {
        TimeMetricEntity entity= new TimeMetricEntity();
        entity.setId(model.getId());
        entity.setTemperature(model.getTemperature());
        entity.setDatetime(model.getDatetime());
        return entity;
    }

    public static Mono<TimeMetricHourDto> toModelMono(Mono<TimeMetricEntity> entity) {
        return entity.flatMap(timeMetricEmtity -> {
            final TimeMetricHourDto timeMetricHourDto = new TimeMetricHourDto();
//            timeMetricHourDto.setId(timeMetricEmtity.getId());
//            timeMetricHourDto.setTemperature(timeMetricEmtity.getTemperature());
//            timeMetricHourDto.setDatetime(timeMetricEmtity.getDatetime());
            return Mono.just(timeMetricHourDto);
        });
    }


}
