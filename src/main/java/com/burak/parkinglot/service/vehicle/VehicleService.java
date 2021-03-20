package com.burak.parkinglot.service.vehicle;

import com.burak.parkinglot.entity.parking.ParkingRecord;
import com.burak.parkinglot.entity.vehicle.Vehicle;

import java.util.List;

public interface VehicleService {
	List<ParkingRecord> getParkingDetails(Vehicle vehicle);

}
	
