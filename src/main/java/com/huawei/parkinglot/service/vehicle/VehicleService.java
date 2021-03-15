package com.huawei.parkinglot.service.vehicle;

import com.huawei.parkinglot.entity.vehicle.Vehicle;

/**
 * Calculations will be placed on each vehicle type
 */
public interface VehicleService {

	void checkOut(Vehicle vehicle);

	void checkIn(Vehicle vehicle);

}