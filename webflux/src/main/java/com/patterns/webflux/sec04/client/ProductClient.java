package com.patterns.webflux.sec04.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.patterns.webflux.sec04.dto.Product;

import reactor.core.publisher.Mono;

@Service
public class ProductClient {
    private static final Logger logger = LoggerFactory.getLogger(ProductClient.class);

    private final WebClient client;

    public ProductClient(@Value(value = "${sec04.product.service}") String baseUrl) {
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<Product> getProduct(Integer id) {
        return this.client
                .get()
                .uri("{id}", id)
                .exchangeToMono(response -> {
                    return response.bodyToMono(Product.class);
                })
                .onErrorResume(ex -> {
                    logger.info("An error occurred while fetching product: {}", ex.getMessage(), ex);
                    return Mono.empty();
                });
    }
}
