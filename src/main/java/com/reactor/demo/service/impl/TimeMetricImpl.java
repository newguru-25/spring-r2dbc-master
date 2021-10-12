package com.reactor.demo.service.impl;

import com.reactor.demo.cast.TimeMetricCast;
import com.reactor.demo.dto.TimeMetricDayDto;
import com.reactor.demo.dto.TimeMetricHourDto;
import com.reactor.demo.dto.TimeMetricRequestDto;
import com.reactor.demo.entity.TimeMetricEntity;
import com.reactor.demo.repository.TimeMetricRepository;
import com.reactor.demo.service.TimeMetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TimeMetricImpl implements TimeMetricService {

    public static final String DATE_TIME = "yyyy-MM-dd HH:mm";
    public static final String DATE = "yyyy-MM-dd";
    private final TimeMetricRepository timeMetricRepository;


    @Override
    public Mono<List<TimeMetricHourDto>> getMetricByHour(String fechaIni, String fechaFin) {
        Flux<TimeMetricEntity> timeMetric = timeMetricRepository.findAllByDatetimeBetween(this.convertToDateTime(fechaIni,DATE_TIME), this.convertToDateTime(fechaFin,DATE_TIME));
        Mono<Integer> minimum = timeMetric.map(TimeMetricEntity::getTemperature).reduce(Math::min);
        Mono<Integer> maximum = timeMetric.map(TimeMetricEntity::getTemperature).reduce(Math::max);
        Mono<Double> average = timeMetric.map(TimeMetricEntity::getTemperature).collect(Collectors.averagingInt(p -> p));
        return Mono.zip(minimum,
                maximum,
                average).flatMap(dFlux ->
                Mono.just(Collections.singletonList(new TimeMetricHourDto().builder().time(splitStringForGettingHours(fechaIni) + " - " + splitStringForGettingHours(fechaFin)).min(dFlux.getT1()).max(dFlux.getT2()).average(dFlux.getT3()).build()))
        );
    }

    @Override
    public Mono<TimeMetricDayDto> getMetricByDay(String fecha) {
        Flux<TimeMetricEntity> timeMetric = timeMetricRepository.findAllByDate(this.convertToDate(fecha,DATE));
        Mono<Integer> minimum = timeMetric.map(TimeMetricEntity::getTemperature).reduce(Math::min);
        Mono<Integer> maximum = timeMetric.map(TimeMetricEntity::getTemperature).reduce(Math::max);
        Mono<Double> average = timeMetric.map(TimeMetricEntity::getTemperature).collect(Collectors.averagingInt(p -> p));
        return Mono.zip(minimum,
                maximum,
                average).flatMap(dFlux ->
                Mono.just(new TimeMetricDayDto().builder().date(splitStringForGettingDay(fecha)).min(dFlux.getT1()).max(dFlux.getT2()).average(dFlux.getT3()).build())
        );
    }

    @Override
    public Mono<Void> save(TimeMetricRequestDto timeMetricRequestDto) {
        return timeMetricRepository.save(TimeMetricCast.toEntity(timeMetricRequestDto)).then();
    }


    private LocalDateTime convertToDateTime(String date, String formatText) {
        DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(formatText);
        return LocalDateTime.parse(date, FORMAT);
    }


    private LocalDate convertToDate(String date, String formatText) {
        DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(formatText);
        return LocalDate.parse(date, FORMAT);
    }

    private String splitStringForGettingHours(String text){
        return text.split(" ")[1];
    }

    private String splitStringForGettingDay(String text){
        return text.split(" ")[0];
    }

}
