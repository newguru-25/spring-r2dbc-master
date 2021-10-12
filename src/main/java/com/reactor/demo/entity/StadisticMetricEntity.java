package com.reactor.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;


@Table("stadistic_time")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StadisticMetricEntity {

    @Id
    private Long id;
    @Column(value = "date")
    private LocalDate date;
    @Column(value = "time")
    private String time;
    @Column(value = "min")
    private Integer min;
    @Column(value = "max")
    private Integer max;
    @Column(value = "average")
    private Double average;

}
