package com.reactor.demo.cast;

import com.reactor.demo.dto.TimeMetricHourDto;
import com.reactor.demo.dto.TimeMetricRequestDto;
import com.reactor.demo.entity.StadisticMetricEntity;
import com.reactor.demo.entity.TimeMetricEntity;
import reactor.core.publisher.Flux;

public class TimeMetricCast {
    public static Flux<TimeMetricHourDto> toModel(Flux<StadisticMetricEntity> entity) {
        return entity.flatMap(timeMetricEmtity -> {
            final TimeMetricHourDto timeMetricHourDto = new TimeMetricHourDto();
            timeMetricHourDto.setMin(timeMetricEmtity.getMin());
            timeMetricHourDto.setMax(timeMetricEmtity.getMax());
            timeMetricHourDto.setAverage(timeMetricEmtity.getAverage());
            timeMetricHourDto.setTime(timeMetricEmtity.getTime());
            return Flux.just(timeMetricHourDto);
        });
    }


    public static TimeMetricEntity toEntity(TimeMetricRequestDto model) {
        TimeMetricEntity entity = new TimeMetricEntity();
        entity.setId(model.getId());
        entity.setTemperature(model.getTemperature());
        entity.setDatetime(model.getDatetime());
        return entity;
    }

}
