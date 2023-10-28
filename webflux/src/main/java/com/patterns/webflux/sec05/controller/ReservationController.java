package com.patterns.webflux.sec05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patterns.webflux.sec05.dto.ReservationItemRequest;
import com.patterns.webflux.sec05.dto.ReservationResponse;
import com.patterns.webflux.sec05.service.ReservationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("sec05")
public class ReservationController {
    
    @Autowired
    private ReservationService service;

    @PostMapping("reserve")
    public Mono<ReservationResponse> reserve(@RequestBody Flux<ReservationItemRequest> flux) {
        return this.service.reserve(flux);
    }



}
