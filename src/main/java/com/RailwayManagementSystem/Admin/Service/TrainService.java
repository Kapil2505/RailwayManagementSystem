package com.RailwayManagementSystem.Admin.Service;

import com.RailwayManagementSystem.Admin.Entity.Train;
import com.RailwayManagementSystem.Admin.Payload.TrainDto;

import java.util.List;

public interface TrainService {

    public Train mapToEntity(TrainDto dto);
    public TrainDto mapToDto(Train train);

    TrainDto addTrainDetails(TrainDto dto);

    void deleteTrainDetails(String trainId);

    TrainDto updateSeatsOfTrain(String trainId, int seat);

    TrainDto getTrain(String trainId);

    List<TrainDto> getAllTrains();
}
