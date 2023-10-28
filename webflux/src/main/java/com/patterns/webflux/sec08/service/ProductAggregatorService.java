package com.patterns.webflux.sec08.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patterns.webflux.sec08.client.ProductClient;
import com.patterns.webflux.sec08.client.ReviewClient;
import com.patterns.webflux.sec08.dto.Product;
import com.patterns.webflux.sec08.dto.ProductAggregate;
import com.patterns.webflux.sec08.dto.Review;

import reactor.core.publisher.Mono;

@Service
public class ProductAggregatorService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private ReviewClient reviewClient;

    public Mono<ProductAggregate> aggregate(Integer id){
        return Mono.zip(
               this.productClient.getProduct(id),
               this.reviewClient.getReviews(id)
        )
        .map(t -> toDto(t.getT1(), t.getT2()));
    }

    private ProductAggregate toDto(Product product, List<Review> reviews){
        return ProductAggregate.create(
                product.getId(),
                product.getCategory(),
                product.getDescription(),
                reviews
        );
    }


}
