package com.burak.parkinglot.service.vehicle;

import com.burak.parkinglot.entity.parking.ParkingRecord;
import com.burak.parkinglot.entity.vehicle.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    public List<ParkingRecord> getParkingDetails(Vehicle vehicle) {

        return vehicle.getParkingRecords();
    }
}
