package com.patterns.webflux.sec04.service;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.patterns.webflux.sec04.dto.OrchestrationRequestContext;
import com.patterns.webflux.sec04.exception.OrderFullfillmentFailure;

import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;

public abstract class Orchestrator {

    public abstract Mono<OrchestrationRequestContext> create(OrchestrationRequestContext ctx);

    public abstract Predicate<OrchestrationRequestContext> isSuccess();

    public abstract Consumer<OrchestrationRequestContext> cancel();

    protected BiConsumer<OrchestrationRequestContext, SynchronousSink<OrchestrationRequestContext>> statusHandler() {
        return (ctx, sink) -> {
            if (isSuccess().test(ctx)) {
                sink.next(ctx);
            } else {
                sink.error(new OrderFullfillmentFailure());
            }
        };
    }
}