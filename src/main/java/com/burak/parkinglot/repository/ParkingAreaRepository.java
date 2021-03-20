package com.burak.parkinglot.repository;

import com.burak.parkinglot.entity.parking.ParkingArea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingAreaRepository  extends CrudRepository<ParkingArea,Long> {

    public Optional<ParkingArea> findParkingAreaByName(String name);
}
