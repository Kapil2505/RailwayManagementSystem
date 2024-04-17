package com.RailwayManagementSystem.User.Service;

import com.RailwayManagementSystem.Admin.Entity.Train;
import com.RailwayManagementSystem.Admin.Payload.TrainDto;
import com.RailwayManagementSystem.User.Entity.Booking;
import com.RailwayManagementSystem.User.PayLoad.BookingDetails;
import com.RailwayManagementSystem.User.PayLoad.PassengerDetails;

import java.util.List;

public interface UserService {
    public Train mapToEntity(TrainDto dto);
    public TrainDto mapToDto(Train train);

    List<TrainDto> findTrainDetailsUsingSourceAndDestination(String source, String destination);

    public BookingDetails mapToBookingDetails(Booking booking);

    BookingDetails bookTrain(PassengerDetails passengerDetails, String trainId);

    BookingDetails getBookingDetails(String bookingId);
}
