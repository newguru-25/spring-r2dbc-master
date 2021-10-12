package com.reactor.demo.controller;


import com.reactor.demo.dto.TimeMetricHourDto;
import com.reactor.demo.entity.TimeMetricEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

public class main {

    public static void main(String[] args) {


        TimeMetricEntity b = new TimeMetricEntity();
        b.setId(1L);
//        19 C°
        b.setTemperature(19);
        LocalTime aa = LocalTime.of(11, 11, 10, 11);
        b.setDatetime(LocalDateTime.of(LocalDate.of(2019, 11, 11), aa));

        TimeMetricEntity d = new TimeMetricEntity();
        d.setId(2L);
        d.setTemperature(12);
        LocalTime bb = LocalTime.of(22, 23, 10, 11);
        d.setDatetime(LocalDateTime.of(LocalDate.of(2019, 11, 11), bb));

        TimeMetricEntity w = new TimeMetricEntity();
        w.setId(3L);
        w.setTemperature(15);
        LocalTime cc = LocalTime.of(17, 20, 10, 11);
        w.setDatetime(LocalDateTime.of(LocalDate.of(2019, 11, 11), cc));


        //List<String> data=new ArrayList<>();


//        Flux.just(b, d, w).subscribe(System.out::println);

//        Flux.just(b, d, w).filter( data ->{
////            System.err.println(data.getTemperature());
//
//            //return data.getTemperature().contains("19 C°");
//        }).map(data->{
//
////            System.err.println(data);
//            return data;
//        }).subscribe(System.out::println);
//        Stream dd=Arrays.asList(b, d, w).stream();
//        System.err.println( Flux.fromStream(dd).toStream().min(Comparator.comparing(TimeMetricEntity::getTemperature)).get());
//        System.err.println( Flux.just(b, d, w).toStream().max(Comparator.comparing(TimeMetricEntity::getTemperature)));
//        System.err.println( Flux.just(b, d, w).toStream().mapToInt(data->data.getTemperature()).average());
//        TimeMetricHourDto a=new TimeMetricHourDto();

        Flux<TimeMetricEntity> timeMetric = Flux.just(b, d, w);

//        timeMetric.map()

//        timeMetric.map(data -> data.getTemperature()).reduce(Math::min).map(data->{
//            System.err.println(data);
//            return new TimeMetricHourDto().builder().min(data).build();
//        }).subscribe(System.out::println);
//        Mono.zip(Mono.just("a"), Mono.just(2))
//                .flatMapMany(tup -> {
//                    return Flux.range(1, tup.getT2()).map(i -> tup.getT1() + i);
//                }).subscribe(System.out::println);

//        Flux<String> fnameFlux = Flux.just("Ramesh","Amit","Vijay");
//        Flux<String> lnameFlux = Flux.just("Sharma","Kumar","Lamba");
//        Flux<String> deptFlux = Flux.just("Admin","IT","Acc.");
//
//        Flux<User> userFlux = Flux.zip(fnameFlux, lnameFlux, deptFlux)
//                .flatMap(dFlux ->
//                        Flux.just(new User(dFlux.getT1(), dFlux.getT2(), dFlux.getT2())));


//        timeMetric.map(data -> data.getTemperature()).collect()


//        timeMetric.map(data -> data.getTemperature()).reduce(Math::min).subscribe(System.out::println);
//        timeMetric.map(data -> data.getTemperature()).reduce(Math::max).subscribe(System.out::println);
//        timeMetric.map(data -> data.getTemperature()).collect(Collectors.averagingInt(p -> p))
//        .subscribe(System.out::println);


        Mono<Integer> sss = timeMetric.map(data -> data.getTemperature()).reduce(Math::min);
        Mono<Integer> ssss = timeMetric.map(data -> data.getTemperature()).reduce(Math::max);
        Mono<Double> sssss = timeMetric.map(data -> data.getTemperature()).collect(Collectors.averagingInt(p -> p));

        Flux<String> fnameFlux = Flux.just("Ramesh", "Amit", "Vijay");
        Flux<String> lnameFlux = Flux.just("Sharma", "Kumar", "Lamba");
        Flux<String> deptFlux = Flux.just("Admin", "IT", "Acc.");

        Mono<TimeMetricHourDto> userFlux= Mono.zip(sss, ssss, sssss).flatMap(dFlux ->
                        Mono.just(new TimeMetricHourDto().builder().min(dFlux.getT1()).build())
        );

        userFlux.subscribe(x -> System.out.println(x));

//        Flux.zip(fnameFlux, lnameFlux, deptFlux)
//                .flatMap(dFlux ->
//                        Flux.just(new User(dFlux.getT1(), dFlux.getT2(), dFlux.getT2())));


//        a.setMin(timeMetric.toStream().min(Comparator.comparing(TimeMetricEntity::getTemperature)).get().getTemperature().intValue());
//        a.setMax(timeMetric.toStream().max(Comparator.comparing(TimeMetricEntity::getTemperature)).get().getTemperature().intValue());
//        a.setAverage(Flux.just(b, d, w).toStream().mapToInt(data->data.getTemperature()).average().getAsDouble());
//        System.err.println(a);
//        a.setDatetime();

//                as(MathFlux.min(Comparator.comparing(TimeMetricEntity::getTemperature)));
    }

    private String splitString(String text){
        return text.split(" ")[1];
    }

}
