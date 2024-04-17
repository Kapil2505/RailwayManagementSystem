package com.RailwayManagementSystem.User.Controller;

import com.RailwayManagementSystem.Admin.Payload.TrainDto;
import com.RailwayManagementSystem.Admin.Repository.TrainRepository;
import com.RailwayManagementSystem.User.PayLoad.BookingDetails;
import com.RailwayManagementSystem.User.PayLoad.PassengerDetails;
import com.RailwayManagementSystem.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userApi")
public class UserController {

    @Autowired
    private UserService userService;


    // http://localhost:8080/userApi/searchTrain
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/searchTrain")
    public ResponseEntity<List<TrainDto>>getTrainDetailsUsingSourceAndDestination(@RequestParam String source,@RequestParam String destination)
    {
        List<TrainDto> list = userService.findTrainDetailsUsingSourceAndDestination(source,destination);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    // http://localhost:8080/userApi/bookTrain/{trainId}
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/bookTrain/{trainId}")
    public ResponseEntity<BookingDetails>bookTrain(@RequestBody PassengerDetails passengerDetails,@PathVariable String trainId)
    {
        BookingDetails details = userService.bookTrain(passengerDetails,trainId);
        return new ResponseEntity<>(details,HttpStatus.CREATED);
    }

    // http://localhost:8080/userApi/getBookingDetails/{bookingId}
    @PreAuthorize("hasRole('USER')")
    @GetMapping("getBookingDetails/{bookingId}")
    public ResponseEntity<BookingDetails>getBookingDetails(@PathVariable String bookingId)
    {
        BookingDetails details = userService.getBookingDetails(bookingId);
        return new ResponseEntity<>(details,HttpStatus.OK);
    }
}
