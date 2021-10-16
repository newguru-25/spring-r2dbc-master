package com.reactor.demo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactor.demo.dto.TimeMetricHourDto;
import com.reactor.demo.entity.TimeMetricEntity;
import com.reactor.demo.util.HourRange;
import com.sun.tools.javac.Main;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class main {

    public static void main(String[] args) throws JsonProcessingException {


        TimeMetricEntity b = new TimeMetricEntity();
        b.setId(1L);
//        19 C°
        b.setTemperature(15);
        LocalTime aa = LocalTime.of(11, 11);
        b.setDatetime(LocalDateTime.of(LocalDate.of(2019, 10, 13), aa));

        TimeMetricEntity d = new TimeMetricEntity();
        d.setId(2L);
        d.setTemperature(30);
        LocalTime bb = LocalTime.of(11, 23);
        d.setDatetime(LocalDateTime.of(LocalDate.of(2019, 10, 13), bb));

        TimeMetricEntity w = new TimeMetricEntity();
        w.setId(3L);
        w.setTemperature(19);
        LocalTime cc = LocalTime.of(16, 20);
        w.setDatetime(LocalDateTime.of(LocalDate.of(2019, 10, 13), cc));

        TimeMetricEntity e = new TimeMetricEntity();
        e.setId(4L);
        e.setTemperature(15);
        LocalTime rr = LocalTime.of(14, 40);
        e.setDatetime(LocalDateTime.of(LocalDate.of(2019, 10, 13), rr));


        Flux<TimeMetricEntity> result = Flux.just(b, d, w, e);

//        System.err.println(matchByHour("11"));

        result.groupBy(data -> data.getDatetime().getHour()
        ).flatMap(Flux::collectList).flatMapIterable(main::getStadistic).subscribe(System.err::println);
//


        //        Mono<Map<Object, List<TimeMetricEntity>>> ssss = result.collect(Collectors.groupingBy(s -> {
//            String hours = String.valueOf(s.getDatetime().getHour());
//            String rangeName = (String) main.matchByHour(hours).get("name");
//            return rangeName;
//        }));

//        result.subscribe(System.out::println);
        return;

//        ssss.flatMapIterable(s -> {
//            List<TimeMetricHourDto> results = new ArrayList<>();
//
////            TimeMetricHourDto
//            for (Map.Entry<Object, List<TimeMetricEntity>> entry : s.entrySet()) {
////                System.out.println(entry.getKey() + "/" + entry.getValue());
//                String key = (String) entry.getKey();
//                final String getHour = key.split("_")[1];
//
//                TimeMetricHourDto timeMetric = TimeMetricHourDto.builder()
//                        .time((String) matchByHour(getHour).get("rangeBetween"))
//                        .min(s.get(entry.getKey()).stream().map(TimeMetricEntity::getTemperature).reduce(Math::min).get())
//                        .max(s.get(entry.getKey()).stream().map(TimeMetricEntity::getTemperature).reduce(Math::max).get())
//                        .average(s.get(entry.getKey()).stream().map(TimeMetricEntity::getTemperature).collect(Collectors.averagingInt(p -> p)))
//                        .build();
//
//                results.add(timeMetric);
//            }
//            results.sort(Comparator.comparing(TimeMetricHourDto::getTime));
//            return results;
//        }).subscribe(System.out::println);
//        ssss.subscribe(System.out::println);

//        .subscribe(System.out::println);


//        Mono<Integer> minimum = timeMetric.map(TimeMetricEntity::getTemperature).reduce(Math::min);
//        Mono<Integer> maximum = timeMetric.map(TimeMetricEntity::getTemperature).reduce(Math::max);
//        Mono<Double> average = timeMetric.map(TimeMetricEntity::getTemperature).collect(Collectors.averagingInt(p -> p));


//        System.err.println(HourRange.valueOf("HOUR_0").getHour());

//        result.subscribe(System.out::println);


//        Mono<List<TimeMetricEntity>> ss = result.collectList();


//        ObjectMapper mapper = new ObjectMapper();
//
//
//      String json=  "[\n" +
//                "                \"01/01/2010\",\n" +
//                "                \"02/01/2010\",\n" +
//                "                \"03/01/2010\",\n" +
//                "                \"05/01/2010\",\n" +
//                "                \"10/01/2010\",\n" +
//                "                \"11/01/2010\",\n" +
//                "                \"22/01/2010\",\n" +
//                "                \"23/01/2010\",\n" +
//                "                \"24/01/2010\"\n" +
//                "]";
//
//        List<String> dateList =Arrays.asList(mapper.readValue(json, String[].class));
//
//
////         = //contains the dates
//                Collections.sort(dateList);
//
//        List resultss = new ArrayList(); // to store the list of intervals
//        for(int i=0; i<dateList.size()-1; i+=2) {
//            List interval = new ArrayList();// to store one interval
//            interval.add(dateList.get(i));
//            interval.add(dateList.get(i+1));
//            resultss.add(interval);
//        }

//        System.err.println(resultss);

        //3 apple, 2 banana, others 1
//        List<Item> items = Arrays.asList(
//                new Item("apple", 10, new BigDecimal("9.99")),
//                new Item("banana", 20, new BigDecimal("19.99")),
//                new Item("orang", 10, new BigDecimal("29.99")),
//                new Item("watermelon", 10, new BigDecimal("29.99")),
//                new Item("papaya", 20, new BigDecimal("9.99")),
//                new Item("apple", 10, new BigDecimal("9.99")),
//                new Item("banana", 10, new BigDecimal("19.98")),
//                new Item("apple", 20, new BigDecimal("9.99"))
//        );
//
////        group by price
//        Map<BigDecimal, List<Item>> groupByPriceMap =
//                items.stream().collect(Collectors.groupingBy(Item::getPrice));
//
//        System.err.println(groupByPriceMap);

    }


    private static List<TimeMetricHourDto> getStadistic(List<TimeMetricEntity> data) {
        String hour = String.valueOf(data.get(0).getDatetime().getHour());
        TimeMetricHourDto timeMetric = TimeMetricHourDto.builder()
                .time((String) matchByHour(hour).get("rangeBetween"))
                .min(data.stream().map(TimeMetricEntity::getTemperature).reduce(Math::min).get())
                .max(data.stream().map(TimeMetricEntity::getTemperature).reduce(Math::max).get())
                .average(data.stream().map(TimeMetricEntity::getTemperature).collect(Collectors.averagingInt(p -> p)))
                .build();
        return Collections.singletonList(timeMetric);
    }

    private static Map<String, Object> matchByHour(String text) {
        Map<String, Object> map = new HashMap<>();
        String constante = "HOUR_";
        try {
            map.put("name", HourRange.valueOf(constante + text).getName());
            map.put("hour", HourRange.valueOf(constante + text).getHour());
            map.put("rangeBetween", HourRange.valueOf(constante + text).getRangeBetweenHours());
            map.put("exist", Boolean.TRUE);
        } catch (Exception e) {
            map.put("name", "none");
            map.put("exist", Boolean.FALSE);
        }
        return map;
    }

}
