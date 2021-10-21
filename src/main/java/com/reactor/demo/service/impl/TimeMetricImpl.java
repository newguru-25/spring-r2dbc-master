package com.reactor.demo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactor.demo.cast.TimeMetricCast;
import com.reactor.demo.controller.main;
import com.reactor.demo.dto.TimeMetricDayDto;
import com.reactor.demo.dto.TimeMetricHourDto;
import com.reactor.demo.dto.TimeMetricRequestDto;
import com.reactor.demo.entity.TimeMetricEntity;
import com.reactor.demo.repository.StadisticRepository;
import com.reactor.demo.repository.TimeMetricRepository;
import com.reactor.demo.service.TimeMetricService;
import com.reactor.demo.util.HourRange;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TimeMetricImpl implements TimeMetricService {

    public static final String DATE_TIME = "yyyy-MM-dd HH:mm";
    public static final String DATE = "yyyy-MM-dd";
    public static final String RANGE_BETWEEN = "rangeBetween";
    private final TimeMetricRepository timeMetricRepository;
    private final StadisticRepository stadisticRepository;

    @Override
    public Flux<TimeMetricHourDto> getMetricByHour(String fecha) {

        Flux<TimeMetricEntity> timeMetrics = timeMetricRepository.findAllByDateByRangeHour(this.convertToDate(fecha, DATE));

        Mono<Map<Object, List<TimeMetricEntity>>> groupingByHours = timeMetrics.collect(Collectors.groupingBy(s -> {
            String hours = String.valueOf(s.getDatetime().getHour());
            return (String) matchByHour(hours).get("name");
        }));

        return groupingByHours.flatMapIterable(s -> {
            List<TimeMetricHourDto> results = new ArrayList<>();
            for (Map.Entry<Object, List<TimeMetricEntity>> entry : s.entrySet()) {
                String key = (String) entry.getKey();
                final String getHour = key.split("_")[1];
                TimeMetricHourDto timeMetric = TimeMetricHourDto.builder()
                        .time((String) matchByHour(getHour).get(RANGE_BETWEEN))
                        .min(s.get(entry.getKey()).stream().map(TimeMetricEntity::getTemperature).reduce(Math::min).get())
                        .max(s.get(entry.getKey()).stream().map(TimeMetricEntity::getTemperature).reduce(Math::max).get())
                        .average(s.get(entry.getKey()).stream().map(TimeMetricEntity::getTemperature).collect(Collectors.averagingInt(p -> p)))
                        .build();

                results.add(timeMetric);
            }
            results.sort(Comparator.comparing(TimeMetricHourDto::getTime));
            return results;
        });
    }

    @Override
    public Flux<List<TimeMetricHourDto>> anotherMethodEquals(String fecha) {
        Flux<TimeMetricEntity> timeMetrics = timeMetricRepository.findAllByDateByRangeHour(this.convertToDate(removeDoubleQuotes(fecha), DATE));
        return timeMetrics.groupBy(TimeMetricImpl::getHour)
                .flatMap(Flux::collectList)
                .flatMapIterable(TimeMetricImpl::getStadistic)
                .sort(Comparator.comparing(TimeMetricHourDto::getTime))
                .flatMap(Flux::just)
                .collectList()
                .flux()
                ;

    }


    public Flux<String> getMetricByHourForWebsocket(String fecha) {
        ObjectMapper mapper = new ObjectMapper();
        Flux<TimeMetricEntity> timeMetrics = timeMetricRepository.findAllByDateByRangeHour(this.convertToDate(removeDoubleQuotes(fecha), DATE));

        return timeMetrics.groupBy(TimeMetricImpl::getHour)
                .flatMap(Flux::collectList)
                .flatMapIterable(TimeMetricImpl::getStadistic)
                .sort(Comparator.comparing(TimeMetricHourDto::getTime))
                .map(datas ->
                        {
                            try {
                                return mapper.writeValueAsString(datas);
                            } catch (JsonProcessingException s) {
                                s.printStackTrace();
                                return null;
                            }
                        }
                )
                ;


    }

    @Override
    public Mono<TimeMetricDayDto> getMetricByDay(String fecha) {
        Flux<TimeMetricEntity> timeMetric = timeMetricRepository.findAllByDate(this.convertToDate(fecha, DATE));
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
    public Flux<TimeMetricHourDto> getStadisticMetricByHours(String fecha) {
        return TimeMetricCast.toModel(stadisticRepository.findAllByDate(this.convertToDate(fecha, DATE)));
    }

    @Override
    public Mono<Void> save(TimeMetricRequestDto timeMetricRequestDto) {
        return timeMetricRepository.save(TimeMetricCast.toEntity(timeMetricRequestDto)).then();
    }


    public String removeDoubleQuotes(String request) {
        if (request.contains("\"\""))
            return request.replace("\"", "");
        else
            return request;
    }


    private LocalDate convertToDate(String date, String formatText) {
        DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(formatText);
        return LocalDate.parse(date, FORMAT);
    }

    private static int getHour(TimeMetricEntity timeMetric) {
        return timeMetric.getDatetime().getHour();
    }

    private String splitStringForGettingDay(String text) {
        return text.split(" ")[0];
    }

    private static List<TimeMetricHourDto> getStadistic(List<TimeMetricEntity> data) {
        String hour = String.valueOf(data.get(0).getDatetime().getHour());
        TimeMetricHourDto timeMetric = TimeMetricHourDto.builder()
                .time((String) matchByHour(hour).get("rangeBetween"))
                .min(data.stream().map(TimeMetricEntity::getTemperature).reduce(Math::min).get())
                .max(data.stream().map(TimeMetricEntity::getTemperature).reduce(Math::max).get())
                .average(data.stream().map(TimeMetricEntity::getTemperature).collect(Collectors.averagingInt(p -> p)))
                .build();
        return Collections.singletonList(timeMetric);
    }

    private static Map<String, Object> matchByHour(String text) {
        Map<String, Object> map = new HashMap<>();
        String constante = "HOUR_";
        try {
            map.put("name", HourRange.valueOf(constante + text).getName());
            map.put("rangeBetween", HourRange.valueOf(constante + text).getRangeBetweenHours());
            map.put("exist", Boolean.TRUE);
        } catch (RuntimeException e) {
            map.put("name", "none");
            map.put("exist", Boolean.FALSE);
        }
        return map;
    }

}
