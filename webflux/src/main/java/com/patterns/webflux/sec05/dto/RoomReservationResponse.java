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
public class RoomReservationResponse {
    
    private UUID reservationId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String category;
    private String city;
    private Integer price;
}
