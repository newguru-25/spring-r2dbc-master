package com.reactor.demo.repository;


import com.reactor.demo.entity.TimeMetricEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface TimeMetricRepository extends R2dbcRepository<TimeMetricEntity, Long> {
}
