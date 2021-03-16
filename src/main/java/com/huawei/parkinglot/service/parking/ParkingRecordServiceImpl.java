package com.huawei.parkinglot.service.parking;

import com.huawei.parkinglot.entity.parking.ParkingArea;
import com.huawei.parkinglot.entity.parking.ParkingRecord;
import com.huawei.parkinglot.entity.parking.PriceData;
import com.huawei.parkinglot.entity.vehicle.Minivan;
import com.huawei.parkinglot.entity.vehicle.SUV;
import com.huawei.parkinglot.entity.vehicle.Sedan;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import com.huawei.parkinglot.exception.ParkingAreaNotFoundException;
import com.huawei.parkinglot.repository.ParkingAreaRepository;
import com.huawei.parkinglot.repository.ParkingRecordRepository;
import com.huawei.parkinglot.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingRecordServiceImpl implements ParkingRecordService {

    ParkingAreaRepository parkingAreaRepository;
    VehicleRepository vehicleRepository;
    ParkingRecordRepository parkingRecordRepository;

    public ParkingRecordServiceImpl(ParkingAreaRepository parkingAreaRepository, VehicleRepository vehicleRepository, ParkingRecordRepository parkingRecordRepository) {
        this.parkingAreaRepository = parkingAreaRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingRecordRepository = parkingRecordRepository;
    }

    @Override
    public void checkIn(LocalDateTime inDate, Vehicle vehicle, Long parkingAreaId) {
        Optional<ParkingArea> parkingAreaOptional = parkingAreaRepository.findById(parkingAreaId);
        if (parkingAreaOptional.isPresent()) {
            Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicle.getId());
            if (isParkingAreaFull(parkingAreaOptional.get())) {
                if (vehicleOptional.isEmpty()) {
                    switch (vehicle.getType()) {
                        case SUV:
                            vehicleRepository.save((SUV) vehicle);
                            break;
                        case SEDAN:
                            vehicleRepository.save((Sedan) vehicle);
                            break;
                        case MINIVAN:
                            vehicleRepository.save((Minivan) vehicle);
                            break;
                    }
                }
                ParkingRecord parkingRecord = new ParkingRecord(inDate, vehicle, parkingAreaOptional.get());
                parkingRecordRepository.save(parkingRecord);
            } else {
                throw ParkingAreaNotFoundException.createWith(parkingAreaId);
            }
        }
    }

    private boolean isParkingAreaFull(ParkingArea parkingArea) {
        List<ParkingRecord> activeRecords = parkingRecordRepository.findByParkingActiveIsTrueAndParkingArea(parkingArea);

        return activeRecords.size() >= parkingArea.getCapacity();
    }

    @Override
    public void checkOut(Long vehicleId) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
        if(vehicleOptional.isPresent()) {
            ParkingRecord activeParkingRecord = parkingRecordRepository.findByParkingActiveIsTrueAndVehicle(vehicleOptional.get());
            ParkingArea parkingArea = activeParkingRecord.getParkingArea();
            activeParkingRecord.setOutDate(LocalDateTime.now());
            long hourDiff = ChronoUnit.HOURS.between(activeParkingRecord.getInDate(), activeParkingRecord.getOutDate());
            Optional<PriceData> priceData = parkingArea.getPriceList().stream().filter(e -> e.getStartHour() <= hourDiff && e.getEndHour() >= hourDiff).findAny();
            if(priceData.isPresent()) {
                double parkingFee = vehicleOptional.get().finalizeParkingFee(priceData.get().getPrice());
                activeParkingRecord.setFee(parkingFee);
                activeParkingRecord.setParkingActive(false);
                parkingRecordRepository.save(activeParkingRecord);
            }
        }

    }


}
