package com.reactor.demo.repository;

import com.reactor.demo.entity.StadisticMetricEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface StadisticRepository extends R2dbcRepository<StadisticMetricEntity, Long> {

    @Query("SELECT * FROM stadistic_time WHERE date LIKE :fecha||'%'")
    Flux<StadisticMetricEntity> findAllByDate(LocalDate fecha);
}
