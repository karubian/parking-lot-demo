package com.huawei.parkinglot.service.vehicle;

import com.huawei.parkinglot.entity.parking.ParkingRecord;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    public List<ParkingRecord> getParkingDetails(Vehicle vehicle) {

        return vehicle.getParkingRecords();
    }
}
