package com.RailwayManagementSystem.User.Entity;

import com.RailwayManagementSystem.Admin.Entity.Train;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bookings")
public class Booking {
    @Id
    private String bookingId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="train_id")
    private Train train;
    @Column(name="passenger_name")
    private String passengerName;
    @Column(name="passenger_age")
    private int  passengerAge;
    @Column(name="booked_seat")
    private String bookedSeat;
    private String source;
    private String destination;
    private String email;
    private String mobile;

    @CreationTimestamp
    @Column(name="booking_time")
    private LocalDateTime bookingTime;





}
