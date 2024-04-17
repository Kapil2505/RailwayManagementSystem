package com.RailwayManagementSystem.User.PayLoad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDetails {
    private String passengerName;
    private int  passengerAge;
    private String email;
    private String mobile;

}
