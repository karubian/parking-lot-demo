package com.huawei.parkinglot.repository;

import com.huawei.parkinglot.entity.vehicle.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle,Long> {
}
