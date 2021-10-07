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

@ExtendWith(MockitoExtension.class)
public class TimeMetricsRepositoryTest {

    @Mock
    TimeMetricRepository timeMetricRepository;

    @Test
    void getAllServiceTest() {

        TimeMetricEntity b = new TimeMetricEntity();
        b.setId(1L);
        b.setTemperature("19 C°");
        LocalTime aa = LocalTime.of(11, 11, 10, 11);
        b.setDatetime(LocalDateTime.of(LocalDate.of(2019, 11, 11), aa));

        Mockito.when(timeMetricRepository.findAll()).thenReturn(Flux.just(b));

        StepVerifier.create(timeMetricRepository.findAll())
                .expectNext(b)
                .expectComplete()
                .verify();
    }

    @Test
    void saveServiceTest() {
        TimeMetricEntity a = new TimeMetricEntity();
        a.setId(1L);
        a.setTemperature("18 C°");
        LocalTime bb = LocalTime.of(11, 11, 10, 11);
        a.setDatetime(LocalDateTime.of(LocalDate.of(2019, 11, 11), bb));

        Mockito.when(timeMetricRepository.save(a)).thenReturn(Mono.just(a));

        StepVerifier.create(timeMetricRepository.save(a))
                .expectNext(a)
                .expectComplete()
                .verify();
    }

}
