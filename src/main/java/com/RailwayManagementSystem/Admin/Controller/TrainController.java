package com.RailwayManagementSystem.Admin.Controller;

import com.RailwayManagementSystem.Admin.Payload.TrainDto;
import com.RailwayManagementSystem.Admin.Service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainApi")
public class TrainController {

    @Autowired
    private TrainService trainService;


    // http://localhost:8080/trainApi/addTrain
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addTrain")
    public ResponseEntity<TrainDto>addTrain(@RequestBody TrainDto dto)
    {
        TrainDto trainDto = trainService.addTrainDetails(dto);
        return new ResponseEntity<>(trainDto, HttpStatus.CREATED);
    }

    // http://localhost:8080/trainApi/deleteTrain/{trainId}
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteTrain/{trainId}")
    public ResponseEntity<?>deleteTrain(@PathVariable String trainId)
    {
        trainService.deleteTrainDetails(trainId);
        return new ResponseEntity<>("train details is deleted",HttpStatus.OK);
    }

    // http://localhost:8080/trainApi/updateSeats/{trainId}
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("updateSeats/{trainId}")
    public ResponseEntity<TrainDto>updateSeats(@PathVariable String trainId,@RequestParam  int seat)
    {
        TrainDto dto = trainService.updateSeatsOfTrain(trainId,seat);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    // http://localhost:8080/trainApi/getTrainDetail/{trainId}
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("getTrainDetail/{trainId}")
    public ResponseEntity<TrainDto>getTrainDetail(@PathVariable String trainId)
    {
        TrainDto dto = trainService.getTrain(trainId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    // http://localhost:8080/trainApi/getAllTrains
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllTrains")
    public ResponseEntity<List<TrainDto>>getTrainsDetails()
    {
        List<TrainDto>list=trainService.getAllTrains();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
