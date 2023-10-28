package com.patterns.webflux.sec05.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.patterns.webflux.sec05.dto.ReservationItemRequest;
import com.patterns.webflux.sec05.dto.ReservationItemResponse;
import com.patterns.webflux.sec05.dto.ReservationResponse;
import com.patterns.webflux.sec05.dto.ReservationType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

@Service
public class ReservationService {

    private final Map<ReservationType, ReservationHandler> handlers;

    public ReservationService(List<ReservationHandler> handlers) {
        this.handlers = handlers.stream()
                .collect(Collectors.toMap(ReservationHandler::getType, Function.identity()));
    }

    public Mono<ReservationResponse> reserve(Flux<ReservationItemRequest> flux) {
        return flux.groupBy(ReservationItemRequest::getReservationType)
                .flatMap(this::aggregate)
                .collectList()
                .map(this::toResponse);
    }

    private Flux<ReservationItemResponse> aggregate(GroupedFlux<ReservationType, ReservationItemRequest> groupedFlux) {
        var key = groupedFlux.key();
        var handler = this.handlers.get(key);
        return handler.reserve(groupedFlux);
    }

    private ReservationResponse toResponse(List<ReservationItemResponse> list) {
        return ReservationResponse.create(UUID.randomUUID(),
                list.stream().mapToInt(ReservationItemResponse::getPrice).sum(), list);
    }

}
