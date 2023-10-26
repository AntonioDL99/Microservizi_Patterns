package com.patterns.webflux.sec04.dto;

import java.util.UUID;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrchestrationRequestContext {
    
    private final UUID orderId = UUID.randomUUID();
    private OrderRequest orderRequest;
    private Integer productPrice;
    private PaymentRequest paymentRequest;
    private PaymentResponse paymentResponse;
    private InventoryRequest inventoryRequest;
    private InventoryResponse inventoryResponse;
    private ShippingRequest shippingRequest;
    private ShippingResponse shippingResponse;
    private Status status;

    public OrchestrationRequestContext(OrderRequest request) {
        this.orderRequest = request;
    }
}
