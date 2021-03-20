package com.burak.parkinglot.repository;

import com.burak.parkinglot.entity.vehicle.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle,Long> {
}
