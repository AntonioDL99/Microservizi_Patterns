package com.patterns.webflux.sec03.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patterns.webflux.sec03.dto.OrchestrationRequestContext;
import com.patterns.webflux.sec03.dto.Status;

import reactor.core.publisher.Mono;

@Service
public class OrderFulfillmentService {

    @Autowired
    private List<Orchestrator> orchestrators;

    public Mono<OrchestrationRequestContext> placeOrder(OrchestrationRequestContext ctx) {
        var list = orchestrators.stream()
                .map(o -> o.create(ctx))
                .collect(Collectors.toList());

        return Mono.zip(list, a -> a[0])
                .cast(OrchestrationRequestContext.class)
                .doOnNext(this::updateStatus);
    }

    private void updateStatus(OrchestrationRequestContext ctx) {
        boolean allSuccess = this.orchestrators.stream().allMatch(o -> o.isSuccess().test(ctx));
        var status = allSuccess ? Status.SUCCESS : Status.FAILED;
        ctx.setStatus(status);
    }

}
