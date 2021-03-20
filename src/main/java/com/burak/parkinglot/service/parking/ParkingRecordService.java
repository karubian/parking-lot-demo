package com.burak.parkinglot.service.parking;

import com.burak.parkinglot.entity.parking.ParkingRecord;
import com.burak.parkinglot.entity.vehicle.Vehicle;

import java.time.LocalDateTime;


public interface ParkingRecordService {

    public ParkingRecord checkIn(LocalDateTime inDate, Vehicle vehicle, Long parkingAreaId);

    public ParkingRecord checkOut(Long vehicleId);

}
