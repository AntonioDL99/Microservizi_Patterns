package com.patterns.webflux.sec05.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class ReservationResponse {
    
    private UUID reservationId;
    private Integer price;
    private List<ReservationItemResponse> items;
}
