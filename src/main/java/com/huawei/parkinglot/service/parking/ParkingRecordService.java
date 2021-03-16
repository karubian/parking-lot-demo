package com.huawei.parkinglot.service.parking;

import com.huawei.parkinglot.entity.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.Date;


public interface ParkingRecordService {

    public void checkIn(LocalDateTime inDate, Vehicle vehicle, Long parkingAreaId);

    public void checkOut(Long vehicleId);

}
