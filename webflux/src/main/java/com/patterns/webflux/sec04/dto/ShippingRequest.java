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
public class ShippingRequest {

    private Integer quantity;
    private Integer userId;
    private UUID inventoryId;
    
}
