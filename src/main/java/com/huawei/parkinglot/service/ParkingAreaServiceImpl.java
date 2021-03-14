package com.huawei.parkinglot.service;

import com.huawei.parkinglot.entity.ParkingArea;
import com.huawei.parkinglot.repository.ParkingAreaRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingAreaServiceImpl implements ParkingAreaService {

    ParkingAreaRepository parkingAreaRepository;

    public ParkingAreaServiceImpl(ParkingAreaRepository parkingAreaRepository) {
        this.parkingAreaRepository = parkingAreaRepository;
    }

    @Override
    public void createParkingArea(ParkingArea parkingArea) {

        parkingAreaRepository.save(parkingArea);
    }


    //Create Parking Area

    //Update Parking Area

    //Delete Parking Area

    //Get Parking area by name

    //Get daily income of a parking area
}
