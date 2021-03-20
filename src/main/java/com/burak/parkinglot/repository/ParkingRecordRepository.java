package com.burak.parkinglot.repository;

import com.burak.parkinglot.entity.parking.ParkingRecord;
import com.burak.parkinglot.entity.vehicle.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParkingRecordRepository extends CrudRepository<ParkingRecord,Long> {
    List<ParkingRecord> findParkingRecordByParkingActiveIsTrue();

    ParkingRecord findByParkingActiveIsTrueAndVehicle(Vehicle vehicle);

}
