package com.patterns.webflux.client;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.patterns.webflux.dto.Review;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import reactor.core.publisher.Mono;

@Service
public class ReviewClient {

    private final WebClient client;

    public ReviewClient(@Value("${sec09.review.service}") String baseUrl){
        this.client = WebClient.builder()
                               .baseUrl(baseUrl)
                               .build();
    }

    @RateLimiter(name = "review-service", fallbackMethod = "fallback") // Client-side
    public Mono<List<Review>> getReviews(Integer id){
        return this.client
                .get()
                .uri("{id}", id)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.empty())
                .bodyToFlux(Review.class)
                .collectList();
                
    }
    
    public Mono<List<Review>> fallback(Integer id, Throwable ex) {
    	return Mono.just(Collections.emptyList());
    }
}
