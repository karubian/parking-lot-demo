package com.huawei.parkinglot.service.vehicle;

import com.huawei.parkinglot.entity.parking.ParkingRecord;
import com.huawei.parkinglot.entity.vehicle.Vehicle;

import java.util.List;

public interface VehicleService {
	List<ParkingRecord> getParkingDetails(Vehicle vehicle);

}
	
