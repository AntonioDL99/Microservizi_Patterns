package com.patterns.webflux;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.patterns.webflux.dto.ProductAggregate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class BulkheadTest {

	private WebClient client;

	@BeforeAll
	public void setClient() {
		this.client = WebClient.builder().baseUrl("http://localhost:8080/sec10").build();
	}

	@Test
	void concurrentUsersTest() {
		StepVerifier.create(Flux.merge(fibRequest(), productRequest()))
				.verifyComplete();

	}

	private Mono<Void> fibRequest() {
		return Flux.range(1, 2).flatMap(i -> this.client.get().uri("fib/46").retrieve().bodyToMono(Long.class))
				.doOnNext(this::print).then();
	}

	private Mono<Void> productRequest() {
		return Mono.delay(Duration.ofMillis(100)).thenMany(Flux.range(1, 2))
				.flatMap(i -> this.client.get().uri("product/1").retrieve().bodyToMono(ProductAggregate.class))
				.map(ProductAggregate::getCategory).doOnNext(this::print).then();
	}

	private void print(Object o) {
		System.out.println(LocalDateTime.now() + " : " + o);
	}
}
