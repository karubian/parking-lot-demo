package com.huawei.parkinglot.repository;

import com.huawei.parkinglot.entity.parking.PriceData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceDataRepository extends CrudRepository<PriceData,Long> {

}
