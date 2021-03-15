package com.huawei.parkinglot.service.parking;

import com.huawei.parkinglot.entity.vehicle.Vehicle;

import java.time.LocalDateTime;


public interface ParkingRecordService {

    public void checkIn(LocalDateTime inDate, Vehicle vehicle, Long parkingAreaId);

    public void checkOut(Vehicle vehicle);

}
