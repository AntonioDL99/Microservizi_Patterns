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
public class CarReservationResponse {
    
    private UUID reservationId;
    private String city;
    private LocalDate pickup;
    private LocalDate drop;
    private String category;
    private Integer price;
}
