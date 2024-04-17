package com.RailwayManagementSystem.User.Service.SeviceImpl;

import com.RailwayManagementSystem.Admin.Entity.Train;
import com.RailwayManagementSystem.Admin.Payload.TrainDto;
import com.RailwayManagementSystem.Admin.Repository.TrainRepository;
import com.RailwayManagementSystem.ExceptionHandler.BookingFailedException;
import com.RailwayManagementSystem.ExceptionHandler.NoAvailableSeatsException;
import com.RailwayManagementSystem.ExceptionHandler.ResourceNotFound;
import com.RailwayManagementSystem.User.Entity.Booking;
import com.RailwayManagementSystem.User.PayLoad.BookingDetails;
import com.RailwayManagementSystem.User.PayLoad.PassengerDetails;
import com.RailwayManagementSystem.User.Repository.BookingRepository;
import com.RailwayManagementSystem.User.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Train mapToEntity(TrainDto dto) {

        return modelMapper.map(dto,Train.class);
    }

    @Override
    public TrainDto mapToDto(Train train) {
        return modelMapper.map(train,TrainDto.class);
    }

    @Override
    public List<TrainDto> findTrainDetailsUsingSourceAndDestination(String source, String destination) {

        List<Train> list = trainRepository.findBySourceAndDestination(source, destination);
        return list.stream().map(x->mapToDto(x)).collect(Collectors.toList());
    }

    @Override
    public BookingDetails mapToBookingDetails(Booking booking) {

        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setBookingId(booking.getBookingId());
        bookingDetails.setPassengerName(booking.getPassengerName());
        bookingDetails.setBookedSeat(booking.getBookedSeat());
        bookingDetails.setSource(booking.getSource());
        bookingDetails.setDestination(booking.getDestination());
        bookingDetails.setBookingTime(booking.getBookingTime());
        return bookingDetails;
    }

    @Transactional
    @Override
    public BookingDetails bookTrain(PassengerDetails passengerDetails, String trainId) {
        Train train = trainRepository.findById(trainId).orElseThrow(()-> new ResourceNotFound("train not found"));
        Booking save;
        if(train.getAvailableSeats()>0)
        {
            Booking booking = new Booking();
            String bookingId = UUID.randomUUID().toString();
            booking.setBookingId(bookingId);
            booking.setTrain(train);
            booking.setPassengerName(passengerDetails.getPassengerName());
            booking.setEmail(passengerDetails.getEmail());
            booking.setMobile(passengerDetails.getMobile());
            booking.setPassengerAge(passengerDetails.getPassengerAge());
            booking.setSource(train.getSource());
            booking.setDestination(train.getDestination());
            booking.setBookedSeat(getSeatNo(trainId));

           save = bookingRepository.save(booking);
            if(save!=null)
            {
                try{
                    train.setAvailableSeats(train.getAvailableSeats()-1);
                    trainRepository.save(train);
                }
                catch (Exception e ) {
                    e.printStackTrace();
                }
            }
            else
            {
                throw new BookingFailedException("booking failed");
            }

        }
        else{
            throw  new NoAvailableSeatsException("Seats are not available");
        }
        return mapToBookingDetails(save);
    }

    @Override
    public BookingDetails getBookingDetails(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFound("booking details not found"));
        return mapToBookingDetails(booking);
    }

    public String getSeatNo(String trainId)
    {
        int seat;
        Train train = trainRepository.findById(trainId).orElseThrow(()-> new ResourceNotFound("train not found"));
        if(train.getTotalSeats()-train.getAvailableSeats()!=0)
        {
            seat = (train.getTotalSeats()-train.getAvailableSeats())+1;
        }
        else
        {
            throw new NoAvailableSeatsException("seats are not available");
        }

        return String.valueOf(seat);
    }

}
