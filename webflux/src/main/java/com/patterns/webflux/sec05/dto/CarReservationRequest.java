package com.patterns.webflux.sec05.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class CarReservationRequest {
    
    private String city;
    private LocalDate pickup;
    private LocalDate drop;
    private String category;
}
