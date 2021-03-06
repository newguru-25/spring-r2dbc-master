package com.reactor.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


import java.time.LocalDateTime;


@Table("time_metric")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeMetricEntity {

    @Id
    private Long id;

    @Column(value = "temperature")
    private Integer temperature;

    @Column(value = "datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime datetime;

}
