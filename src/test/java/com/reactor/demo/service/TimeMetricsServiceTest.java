package com.reactor.demo.service;

import com.reactor.demo.dto.TimeMetricDto;
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
public class TimeMetricsServiceTest {

    @Mock
    TimeMetricService service;

    @Test
    void getAllServiceTest() {

        TimeMetricDto b = new TimeMetricDto();
        b.setId(1L);
        b.setTemperature("19 C°");
        LocalTime aa = LocalTime.of(11, 11, 10, 11);
        b.setDatetime(LocalDateTime.of(LocalDate.of(2019, 11, 11), aa));

        Mockito.when(service.getAll()).thenReturn(Flux.just(b));

        StepVerifier.create(service.getAll())
                .expectNext(b)
                .expectComplete()
                .verify();
    }

    @Test
    void saveServiceTest() {
        TimeMetricDto a = new TimeMetricDto();
        a.setId(1L);
        a.setTemperature("18 C°");
        LocalTime bb = LocalTime.of(11, 11, 10, 11);
        a.setDatetime(LocalDateTime.of(LocalDate.of(2019, 11, 11), bb));

        Mockito.when(service.save(a)).thenReturn(Mono.just(a));

        StepVerifier.create(service.save(a))
                .expectNext(a)
                .expectComplete()
                .verify();
    }

}
