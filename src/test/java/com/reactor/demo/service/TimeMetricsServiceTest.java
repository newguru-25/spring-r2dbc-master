package com.reactor.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactor.demo.dto.TimeMetricDayDto;
import com.reactor.demo.dto.TimeMetricHourDto;
import com.reactor.demo.dto.TimeMetricRequestDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TimeMetricsServiceTest {

    @Mock
    TimeMetricService service;
    ObjectMapper mapper = new ObjectMapper();


    @Test
    void getMetricByHourTest() throws JsonProcessingException {
        Mockito.when(service.getMetricByHour("2019-10-13 11:11", "2019-12-25 23:11")).thenReturn(Mono.just(buildingHourResponse()));

        StepVerifier.create(service.getMetricByHour("2019-10-13 11:11", "2019-12-25 23:11"))
                .expectNext(buildingHourResponse())
                .expectComplete()
                .verify();

    }

    @Test
    void getMetricByDayTest() throws JsonProcessingException {
        Mockito.when(service.getMetricByDay("2019-10-13")).thenReturn(Mono.just(this.buildingByDayResponse()));

        StepVerifier.create(service.getMetricByDay("2019-10-13"))
                .expectNext(this.buildingByDayResponse())
                .expectComplete()
                .verify();

    }

    @Test
    void saveServiceTest() {
        TimeMetricRequestDto a = new TimeMetricRequestDto();
        a.setId(1L);
        a.setTemperature(18);
        LocalTime bb = LocalTime.of(11, 11, 10, 11);
        a.setDatetime(LocalDateTime.of(LocalDate.of(2019, 11, 11), bb));

        Mono<TimeMetricRequestDto> m = Mono.just(a);
        Mono<Void> v = m.then();
        Mockito.when(service.save(a)).thenReturn(v);

        StepVerifier.create(service.save(a))
                .verifyComplete();
    }


    public List<TimeMetricHourDto> buildingHourResponse() throws JsonProcessingException {
        String json = "[\n" +
                "    {\n" +
                "        \"time\": \"11:11 - 23:11\",\n" +
                "        \"min\": 12,\n" +
                "        \"max\": 18,\n" +
                "        \"average\": 14.666666666666666\n" +
                "    }\n" +
                "]";

        return Arrays.asList(mapper.readValue(json, TimeMetricHourDto[].class));
    }

    public TimeMetricDayDto buildingByDayResponse() throws JsonProcessingException {
        String json = "  {\n" +
                "        \"date\": \"2019-10-13\",\n" +
                "            \"min\": 18,\n" +
                "            \"max\": 18,\n" +
                "            \"average\": 18.0\n" +
                "    }";

        return mapper.readValue(json, TimeMetricDayDto.class);
    }


}
