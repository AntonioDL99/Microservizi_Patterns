package com.patterns.webflux.sec04.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class ShippingResponse {
    
    private UUID shippingId;
    private Integer quantity;
    private Status status;
    private String expectedDelivery;
    private Address address;
    
}
