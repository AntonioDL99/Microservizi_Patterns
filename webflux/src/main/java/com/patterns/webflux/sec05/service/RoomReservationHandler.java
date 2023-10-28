package com.patterns.webflux.sec05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patterns.webflux.sec05.client.RoomClient;
import com.patterns.webflux.sec05.dto.ReservationItemRequest;
import com.patterns.webflux.sec05.dto.ReservationItemResponse;
import com.patterns.webflux.sec05.dto.ReservationType;
import com.patterns.webflux.sec05.dto.RoomReservationRequest;
import com.patterns.webflux.sec05.dto.RoomReservationResponse;

import reactor.core.publisher.Flux;

@Service
public class RoomReservationHandler extends ReservationHandler {

    @Autowired
    private RoomClient client;

    @Override
    protected ReservationType getType() {
        return ReservationType.ROOM;
    }

    @Override
    protected Flux<ReservationItemResponse> reserve(Flux<ReservationItemRequest> flux) {
        return flux.map(this::toRoomRequest)
                .transform(this.client::getRoomReservations)
                .map(this::toResponse);
    }

    private RoomReservationRequest toRoomRequest(ReservationItemRequest request) {
        return RoomReservationRequest.create(request.getCity(), request.getFrom(), request.getTo(),
                request.getCategory());
    }

    private ReservationItemResponse toResponse(RoomReservationResponse response) {
        return ReservationItemResponse.create(response.getReservationId(), getType(), response.getCategory(),
                response.getCity(), response.getCheckIn(), response.getCheckOut(), response.getPrice());
    }
}
