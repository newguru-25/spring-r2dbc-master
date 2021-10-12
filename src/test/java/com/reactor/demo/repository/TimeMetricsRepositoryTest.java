package com.reactor.demo.repository;


import com.reactor.demo.entity.TimeMetricEntity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
public class TimeMetricsRepositoryTest {

    @Mock
    TimeMetricRepository timeMetricRepository;
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm";
    public static final String DATE = "yyyy-MM-dd";

    @Test
    void findAllByDatetimeBetweenTest() {
        TimeMetricEntity a = new TimeMetricEntity();
        a.setId(1L);
        a.setTemperature(18);
        LocalTime bb = LocalTime.of(11, 11, 10, 11);
        a.setDatetime(LocalDateTime.of(LocalDate.of(2019, 11, 11), bb));

        Mockito.when(timeMetricRepository.findAllByDatetimeBetween(this.convertToDateTime("2019-10-13 11:11", DATE_TIME), this.convertToDateTime("2019-12-25 23:11", DATE_TIME))).thenReturn(Flux.just(a));

        StepVerifier.create(timeMetricRepository.findAllByDatetimeBetween(this.convertToDateTime("2019-10-13 11:11", DATE_TIME), this.convertToDateTime("2019-12-25 23:11", DATE_TIME)))
                .expectNext(a)
                .expectComplete()
                .verify();
    }

    @Test
    void findAllByDateTest() {
        TimeMetricEntity a = new TimeMetricEntity();
        a.setId(1L);
        a.setTemperature(18);
        LocalTime bb = LocalTime.of(11, 11, 10, 11);
        a.setDatetime(LocalDateTime.of(LocalDate.of(2019, 11, 11), bb));

        Mockito.when(timeMetricRepository.findAllByDate(this.convertToDate("2019-10-13", DATE))).thenReturn(Flux.just(a));

        StepVerifier.create(timeMetricRepository.findAllByDate(this.convertToDate("2019-10-13", DATE)))
                .expectNext(a)
                .expectComplete()
                .verify();
    }

    @Test
    void saveServiceTest() {
        TimeMetricEntity a = new TimeMetricEntity();
        a.setId(1L);
        a.setTemperature(18);
        LocalTime bb = LocalTime.of(11, 11, 10, 11);
        a.setDatetime(LocalDateTime.of(LocalDate.of(2019, 11, 11), bb));

        Mockito.when(timeMetricRepository.save(a)).thenReturn(Mono.just(a));

        StepVerifier.create(timeMetricRepository.save(a))
                .expectNext(a)
                .expectComplete()
                .verify();
    }


    private LocalDateTime convertToDateTime(String date, String formatText) {
        DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(formatText);
        return LocalDateTime.parse(date, FORMAT);
    }


    private LocalDate convertToDate(String date, String formatText) {
        DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(formatText);
        return LocalDate.parse(date, FORMAT);
    }

}
