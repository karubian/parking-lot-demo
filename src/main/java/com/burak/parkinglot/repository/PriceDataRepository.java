package com.burak.parkinglot.repository;

import com.burak.parkinglot.entity.parking.PriceData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceDataRepository extends CrudRepository<PriceData,Long> {

}
