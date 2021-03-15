package com.huawei.parkinglot.repository;

import com.huawei.parkinglot.entity.parking.ParkingRecord;
import org.springframework.data.repository.CrudRepository;

public interface ParkingRecordRepository extends CrudRepository<ParkingRecord,Long> {
}
