package com.reactor.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeMetricHourDto implements Serializable {


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private String time;
    private Integer min;
    private Integer max;
    private Double average;
    private Integer unidad;


}
