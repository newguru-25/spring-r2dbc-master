package com.reactor.demo.service.impl;

import com.reactor.demo.config.EmitPublisher;
import com.reactor.demo.service.TimeMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class WebFluxWebSocket implements WebSocketHandler {

    @Autowired
    TimeMetricService timeMetricService;
    private final EmitPublisher emitPublisher;
    private Flux<String> publisher;

    public WebFluxWebSocket(EmitPublisher emitPublisher) {
        this.emitPublisher = emitPublisher;
        this.publisher = Flux.create(emitPublisher).share();
    }

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        webSocketSession
                .receive()
                .map(WebSocketMessage::getPayloadAsText)
                .map(params -> timeMetricService.getMetricByHourForWebsocket(params))
                .subscribe(greetings -> greetings.subscribe(emitPublisher::push)
                );

        final Flux<WebSocketMessage> message = publisher
                .map(webSocketSession::textMessage
                );

        return webSocketSession.send(message);

    }


}