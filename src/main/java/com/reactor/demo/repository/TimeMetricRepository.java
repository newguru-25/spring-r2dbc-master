package com.reactor.demo.repository;

import com.reactor.demo.entity.TimeMetricEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TimeMetricRepository extends R2dbcRepository<TimeMetricEntity, Long> {
    @Query("SELECT * FROM time_metric WHERE datetime >= :fechaIni AND datetime <= :fechaFin")
    Flux<TimeMetricEntity> findAllByDatetimeBetween(LocalDateTime fechaIni, LocalDateTime fechaFin);

    @Query("SELECT * FROM time_metric WHERE datetime LIKE :fecha||'%'")
    Flux<TimeMetricEntity> findAllByDateByRangeHour(LocalDate fecha);

    @Query("SELECT * FROM time_metric WHERE datetime LIKE :fecha||'%'")
    Flux<TimeMetricEntity> findAllByDate(LocalDate fecha);
}
