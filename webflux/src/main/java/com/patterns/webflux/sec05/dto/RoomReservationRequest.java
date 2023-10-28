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
public class RoomReservationRequest {
    
    private String city;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String category;
}
