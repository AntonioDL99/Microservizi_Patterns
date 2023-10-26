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
public class PaymentResponse {
    
    private UUID paymentId;
    private Integer userId;
    private String name;
    private Integer balance;
    private Status status;
    
}
