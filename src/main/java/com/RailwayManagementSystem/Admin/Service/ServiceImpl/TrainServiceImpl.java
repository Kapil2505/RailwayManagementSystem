package com.RailwayManagementSystem.Admin.Service.ServiceImpl;


import com.RailwayManagementSystem.Admin.Entity.Train;
import com.RailwayManagementSystem.Admin.Payload.TrainDto;
import com.RailwayManagementSystem.Admin.Repository.TrainRepository;
import com.RailwayManagementSystem.Admin.Service.TrainService;
import com.RailwayManagementSystem.ExceptionHandler.ResourceNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TrainRepository trainRepo;
    @Override
    public Train mapToEntity(TrainDto dto) {

        return modelMapper.map(dto,Train.class);
    }

    @Override
    public TrainDto mapToDto(Train train) {
        return modelMapper.map(train,TrainDto.class);
    }

    @Override
    public TrainDto addTrainDetails(TrainDto dto) {
        Train train = mapToEntity(dto);
        String trainId = UUID.randomUUID().toString();
        train.setTrainId(trainId);

        return mapToDto(trainRepo.save(train));
    }
    @Override
    public void deleteTrainDetails(String trainId) {
        trainRepo.findById(trainId).orElseThrow(()-> new ResourceNotFound("train not found"));
        trainRepo.deleteById(trainId);
    }

    @Override
    public TrainDto updateSeatsOfTrain(String trainId, int seat) {

        Train train = trainRepo.findById(trainId).orElseThrow(()-> new ResourceNotFound("train not found"));
        train.setTotalSeats(seat);
        return mapToDto(trainRepo.save(train));
    }

    @Override
    public TrainDto getTrain(String trainId) {
        Train train = trainRepo.findById(trainId).orElseThrow(() -> new ResourceNotFound("train not found"));
        return mapToDto( train);
    }

    @Override
    public List<TrainDto> getAllTrains() {
        List<Train>list = trainRepo.findAll();
        return list.stream().map(x->mapToDto(x)).collect(Collectors.toList()) ;
    }
}
