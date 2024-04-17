package com.RailwayManagementSystem.User.Repository;

import com.RailwayManagementSystem.User.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,String> {

}
