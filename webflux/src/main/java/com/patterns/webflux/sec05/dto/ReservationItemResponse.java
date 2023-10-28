package com.patterns.webflux.sec05.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class ReservationItemResponse {
    
    private UUID itemId;
    private ReservationType reservationType;
    private String category;
    private String city;
    private LocalDate from;
    private LocalDate to;
    private Integer price;
}
