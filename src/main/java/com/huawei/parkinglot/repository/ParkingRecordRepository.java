package com.huawei.parkinglot.repository;

import com.huawei.parkinglot.entity.parking.ParkingArea;
import com.huawei.parkinglot.entity.parking.ParkingRecord;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParkingRecordRepository extends CrudRepository<ParkingRecord,Long> {
    List<ParkingRecord> findParkingRecordByParkingActiveIsTrue();

    ParkingRecord findByParkingActiveIsTrueAndVehicle(Vehicle vehicle);

}
