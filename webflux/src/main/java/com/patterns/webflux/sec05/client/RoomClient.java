package com.patterns.webflux.sec05.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.patterns.webflux.sec05.dto.RoomReservationRequest;
import com.patterns.webflux.sec05.dto.RoomReservationResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RoomClient {

    private final WebClient client;

    public RoomClient(@Value("${sec05.room.service}") String baseUrl) {
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Flux<RoomReservationResponse> getRoomReservations(Flux<RoomReservationRequest> flux) {
        return client
                .post()
                .body(flux, RoomReservationRequest.class)
                .retrieve()
                .bodyToFlux(RoomReservationResponse.class)
                .onErrorResume(ex -> Mono.empty());
    }
}
