package com.huawei.parkinglot.service.parking;


import com.huawei.parkinglot.entity.parking.ParkingArea;

public interface ParkingAreaService {

    public void createParkingArea(ParkingArea parkingArea);
    public void updateParkingArea(ParkingArea parkingArea,Long id);
    public void deleteParkingArea(Long id);
    public ParkingArea getParkingAreaByName(String name);
}
