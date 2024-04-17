package com.RailwayManagementSystem.User.PayLoad;

import com.RailwayManagementSystem.Admin.Entity.Train;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails {
    private String bookingId;
    private String passengerName;
    private String bookedSeat;
    private String source;
    private String destination;
    private LocalDateTime bookingTime;
}
