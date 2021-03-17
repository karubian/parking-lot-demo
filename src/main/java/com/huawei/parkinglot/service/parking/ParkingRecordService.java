package com.huawei.parkinglot.service.parking;

import com.huawei.parkinglot.entity.parking.ParkingRecord;
import com.huawei.parkinglot.entity.vehicle.Vehicle;

import java.time.LocalDateTime;


public interface ParkingRecordService {

    public ParkingRecord checkIn(LocalDateTime inDate, Vehicle vehicle, Long parkingAreaId);

    public ParkingRecord checkOut(Long vehicleId);

}
