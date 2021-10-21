package com.reactor.demo.config;

import com.reactor.demo.service.impl.WebFluxWebSocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;


import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {

    public static final String MAIN_ROOT = "/timemetricws";
    public static final String RANGOHORA_ENDPOINT = MAIN_ROOT + "/rangohora";
    public static final String RANGODIA_ENDPOINT = MAIN_ROOT + "/rangodia";

    private WebSocketHandler webSocketHandler;

    public WebFluxConfig(WebFluxWebSocket webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*");
    }

    @Bean
    public HandlerMapping handlerMapping() {
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put(RANGOHORA_ENDPOINT, webSocketHandler);
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setUrlMap(map);
        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return mapping;
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}