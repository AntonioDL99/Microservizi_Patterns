package com.patterns.webflux.sec05.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReservationItemRequest {
    
    private ReservationType reservationType;
    private String category;
    private String city;
    private LocalDate from;
    private LocalDate to;
    
}
