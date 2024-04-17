package com.RailwayManagementSystem.Admin.Entity;

import com.RailwayManagementSystem.User.Entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="trains")
public class Train {

    @Id
    private String trainId;
    private String source;
    private String destination;
   @Column(name="total_seats")
    private int totalSeats;
   @Column(name="available_seats")
    private int availableSeats;

   @OneToMany(mappedBy = "train",cascade = CascadeType.ALL,orphanRemoval = true)
   List<Booking>bookings= new ArrayList<>();
}
