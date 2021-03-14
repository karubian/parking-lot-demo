package com.huawei.parkinglot.repository;

import com.huawei.parkinglot.entity.ParkingArea;
import com.huawei.parkinglot.entity.PriceData;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceDataRepository extends CrudRepository<PriceData,Long> {

    List<PriceData> findByParkingArea(ParkingArea parkingArea, Sort sort);
}
