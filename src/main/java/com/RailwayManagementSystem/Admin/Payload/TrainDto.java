package com.RailwayManagementSystem.Admin.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainDto {
    private String trainId;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;
}
