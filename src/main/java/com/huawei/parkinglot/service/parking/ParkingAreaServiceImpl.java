package com.huawei.parkinglot.service.parking;

import com.huawei.parkinglot.entity.parking.ParkingArea;
import com.huawei.parkinglot.exception.ParkingAreaNotFoundException;
import com.huawei.parkinglot.repository.ParkingAreaRepository;
import com.huawei.parkinglot.repository.PriceDataRepository;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
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
    public ParkingArea createParkingArea(ParkingArea parkingArea) {
        return parkingAreaRepository.save(parkingArea);
    }

    @Override
    public ParkingArea updateParkingArea(ParkingArea parkingArea, Long id) throws ParkingAreaNotFoundException {
        Optional<ParkingArea> optionalParkingArea = parkingAreaRepository.findById(id);
        if(!optionalParkingArea.isPresent()) {
            throw ParkingAreaNotFoundException.createWith(id);
        }
        parkingArea.setId(id);

        return parkingAreaRepository.save(parkingArea);
    }

    @Override
    public void deleteParkingArea(Long id) throws ParkingAreaNotFoundException {
        ParkingArea parkingArea = parkingAreaRepository.findById(id).orElseThrow(() -> ParkingAreaNotFoundException.createWith(id));

        parkingAreaRepository.delete(parkingArea);

    }

    @Override
    public ParkingArea getParkingAreaByName(String name) throws ParkingAreaNotFoundException {
        return parkingAreaRepository.findParkingAreaByName(name).orElseThrow(() -> ParkingAreaNotFoundException.createWith(name));
    }

    @Override
    public double getDailyIncomeOfParkingArea(Date date, Long id) {
        ParkingArea parkingArea = parkingAreaRepository.findById(id).orElseThrow(() -> ParkingAreaNotFoundException.createWith(id));
        return parkingArea.getParkingRecords().stream() /* .filter(e -> isSameDay(e.getInDate(),date)) */ .map( e -> e.getFee()).reduce(0.0,Double::sum);
    }

    private boolean isSameDay(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }
}
