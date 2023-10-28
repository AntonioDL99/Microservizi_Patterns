package com.patterns.webflux.sec08.client;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.patterns.webflux.sec08.dto.Review;

import reactor.core.publisher.Mono;

@Service
public class ReviewClient {

    private final WebClient client;

    public ReviewClient(@Value("${sec07.review.service}") String baseUrl) {
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<List<Review>> getReviews(Integer id) {
        return this.client
                .get()
                .uri("{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToFlux(Review.class)
                .collectList()
                .retry(5)
                .timeout(Duration.ofMillis(300))
                .onErrorReturn(Collections.emptyList());
    }

}
