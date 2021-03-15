package com.huawei.parkinglot.service.parking;

import com.huawei.parkinglot.entity.parking.ParkingArea;
import com.huawei.parkinglot.repository.ParkingAreaRepository;
import com.huawei.parkinglot.repository.PriceDataRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingAreaServiceImpl implements ParkingAreaService {

    ParkingAreaRepository parkingAreaRepository;
    PriceDataRepository priceDataRepository;

    public ParkingAreaServiceImpl(ParkingAreaRepository parkingAreaRepository, PriceDataRepository priceDataRepository) {
        this.parkingAreaRepository = parkingAreaRepository;
        this.priceDataRepository = priceDataRepository;
    }

    @Override
    public void createParkingArea(ParkingArea parkingArea) {
        //parkingArea.getPriceList().forEach(e -> e.setParkingArea(parkingArea));
        parkingAreaRepository.save(parkingArea);
    }

    @Override
    public void updateParkingArea(ParkingArea parkingArea, Long id) {
        Optional<ParkingArea> parkingAreaOptional = parkingAreaRepository.findById(id);

        if (parkingAreaOptional.isPresent()) {
            parkingArea.setId(id);

            parkingAreaRepository.save(parkingArea);
        }
    }

    @Override
    public void deleteParkingArea(Long id) {
        Optional<ParkingArea> parkingAreaOptional = parkingAreaRepository.findById(id);

        if (parkingAreaOptional.isPresent()) {
            parkingAreaRepository.delete(parkingAreaOptional.get());
        }
    }

    @Override
    public ParkingArea getParkingAreaByName(String name) {
        Optional<ParkingArea> parkingAreaOptional = parkingAreaRepository.findParkingAreaByName(name);
        if(parkingAreaOptional.isPresent()) {
            return parkingAreaOptional.get();
        }
        return null;
    }



    //Get Parking area by name

    //Get daily income of a parking area
}
