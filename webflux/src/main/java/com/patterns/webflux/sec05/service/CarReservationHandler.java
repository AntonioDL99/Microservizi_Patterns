package com.patterns.webflux.sec05.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.patterns.webflux.sec05.client.CarClient;
import com.patterns.webflux.sec05.dto.CarReservationRequest;
import com.patterns.webflux.sec05.dto.CarReservationResponse;
import com.patterns.webflux.sec05.dto.ReservationItemRequest;
import com.patterns.webflux.sec05.dto.ReservationItemResponse;
import com.patterns.webflux.sec05.dto.ReservationType;

import reactor.core.publisher.Flux;

public class CarReservationHandler extends ReservationHandler {

    @Autowired
    private CarClient client;

    @Override
    protected ReservationType getType() {
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }

    @Override
    protected Flux<ReservationItemResponse> reserve(Flux<ReservationItemRequest> flux) {
        return flux.map(this::toCarRequest)
                .transform(this.client::getCarReservations)
                .map(this::toResponse);
    }

    private CarReservationRequest toCarRequest(ReservationItemRequest request) {
        return CarReservationRequest.create(request.getCity(), request.getFrom(), request.getTo(),
                request.getCategory());
    }

    private ReservationItemResponse toResponse(CarReservationResponse response) {
        return ReservationItemResponse.create(response.getReservationId(), getType(), response.getCategory(),
                response.getCity(), response.getPickup(), response.getDrop(), response.getPrice());
    }
}
