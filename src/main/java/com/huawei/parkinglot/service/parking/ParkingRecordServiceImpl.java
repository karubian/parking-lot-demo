package com.huawei.parkinglot.service.parking;

import com.huawei.parkinglot.entity.parking.ParkingArea;
import com.huawei.parkinglot.entity.parking.ParkingRecord;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import com.huawei.parkinglot.repository.ParkingAreaRepository;
import com.huawei.parkinglot.repository.ParkingRecordRepository;
import com.huawei.parkinglot.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ParkingRecordServiceImpl implements ParkingRecordService {

    ParkingAreaRepository parkingAreaRepository;
    VehicleRepository vehicleRepository;
    ParkingRecordRepository parkingRecordRepository;

    public ParkingRecordServiceImpl(ParkingAreaRepository parkingAreaRepository, VehicleRepository vehicleRepository, ParkingRecordRepository parkingRecordRepository) {
        this.parkingAreaRepository = parkingAreaRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingRecordRepository = parkingRecordRepository;
    }

    @Override
    public void checkIn(LocalDateTime inDate, Vehicle vehicle, Long parkingAreaId) {
        Optional<ParkingArea> parkingAreaOptional = parkingAreaRepository.findById(parkingAreaId);
        if (parkingAreaOptional.isPresent()) {
            Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicle.getId());
            if (!parkingAreaOptional.get().isFull()) {
                if (!vehicleOptional.isPresent()) {
                    vehicleRepository.save(vehicle);
                }

                ParkingRecord parkingRecord = new ParkingRecord(inDate, vehicle, parkingAreaOptional.get());
                parkingRecordRepository.save(parkingRecord);
            } else {
                // throw exception
            }
        }
    }

    @Override
    public void checkOut(Vehicle vehicle) {

    }


}
