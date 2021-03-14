package com.huawei.parkinglot.service;

import com.huawei.parkinglot.dto.ParkingRecordDto;
import com.huawei.parkinglot.entity.ParkingArea;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import com.huawei.parkinglot.repository.ParkingAreaRepository;
import com.huawei.parkinglot.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingRecordServiceImpl implements ParkingRecordService {

    ParkingAreaRepository parkingAreaRepository;
    VehicleRepository vehicleRepository;

    public ParkingRecordServiceImpl(ParkingAreaRepository parkingAreaRepository,VehicleRepository vehicleRepository) {
        this.parkingAreaRepository = parkingAreaRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public void checkIn(ParkingRecordDto parkingRecordDto) {
        Optional<ParkingArea> parkingAreaOptional = parkingAreaRepository.findById(parkingRecordDto.getParkingAreaId());
        if(parkingAreaOptional.isPresent()) {
            Optional<Vehicle> vehicleOptional = vehicleRepository.findById(parkingRecordDto.getVehicleId());
            if(vehicleOptional.isPresent()) {
                // create parking record with current date
            } else {
                // create vehicle then record with the current date
            }
        }
    }
}
