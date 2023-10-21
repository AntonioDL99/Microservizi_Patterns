package com.patterns.webflux.sec03.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.patterns.webflux.sec03.dto.PaymentRequest;
import com.patterns.webflux.sec03.dto.PaymentResponse;
import com.patterns.webflux.sec03.dto.Status;

import reactor.core.publisher.Mono;

@Service
public class UserClient {

    private static final String DEDUCT = "deduct";
    private final static String REFUND = "refund";
    private final WebClient client;

    public UserClient(@Value(value = "${sec03.user.service}") String baseUrl) {
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<PaymentResponse> deduct(PaymentRequest request) {
        return this.callUserService(DEDUCT, request);
    }

    public Mono<PaymentResponse> refund(PaymentRequest request) {
        return this.callUserService(REFUND, request);
    }

    private Mono<PaymentResponse> callUserService(String endPoint, PaymentRequest request) {
        return this.client
                .post()
                .uri(endPoint)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .onErrorReturn(this.buildErrorResponse(request));
    }

    private PaymentResponse buildErrorResponse(PaymentRequest request) {
        return PaymentResponse.create(request.getUserId(), null, request.getAmount(), Status.FAILED);
    }
}
