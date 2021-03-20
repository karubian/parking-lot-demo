package com.burak.parkinglot.service.parking;


import com.burak.parkinglot.entity.parking.ParkingArea;
import com.burak.parkinglot.exception.ParkingAreaNotFoundException;

import java.util.Date;

public interface ParkingAreaService {

    public ParkingArea createParkingArea(ParkingArea parkingArea);
    public ParkingArea updateParkingArea(ParkingArea parkingArea,Long id) throws ParkingAreaNotFoundException;
    public void deleteParkingArea(Long id) throws ParkingAreaNotFoundException;
    public ParkingArea getParkingAreaByName(String name) throws ParkingAreaNotFoundException;
    public double getDailyIncomeOfParkingArea(Date date, Long id);
}
