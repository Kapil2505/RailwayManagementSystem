package com.RailwayManagementSystem.Admin.Repository;

import com.RailwayManagementSystem.Admin.Entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train,String> {

    List<Train>findBySourceAndDestination(String source,String destination);
}
