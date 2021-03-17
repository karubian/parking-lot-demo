package com.huawei.parkinglot.service.parking;

import com.huawei.parkinglot.entity.parking.ParkingArea;
import com.huawei.parkinglot.entity.parking.ParkingRecord;
import com.huawei.parkinglot.entity.parking.PriceData;
import com.huawei.parkinglot.entity.vehicle.Minivan;
import com.huawei.parkinglot.entity.vehicle.SUV;
import com.huawei.parkinglot.entity.vehicle.Sedan;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import com.huawei.parkinglot.exception.ParkingAreaInsufficientCapacityException;
import com.huawei.parkinglot.exception.ParkingAreaNotFoundException;
import com.huawei.parkinglot.repository.ParkingAreaRepository;
import com.huawei.parkinglot.repository.ParkingRecordRepository;
import com.huawei.parkinglot.repository.VehicleRepository;
import com.huawei.parkinglot.util.TimeFactory;
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
    TimeFactory clock;
    public ParkingRecordServiceImpl(ParkingAreaRepository parkingAreaRepository, VehicleRepository vehicleRepository, ParkingRecordRepository parkingRecordRepository,TimeFactory clock) {
        this.parkingAreaRepository = parkingAreaRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingRecordRepository = parkingRecordRepository;
        this.clock = clock;
    }

    @Override
    public ParkingRecord checkIn(LocalDateTime inDate, Vehicle vehicle, Long parkingAreaId) {
        Optional<ParkingArea> parkingAreaOptional = parkingAreaRepository.findById(parkingAreaId);
        if (parkingAreaOptional.isPresent()) {
            if (!isParkingAreaFull(parkingAreaOptional.get())) {
                Vehicle persistedVehicle = null;
                if (vehicle.getId() != null) {
                    Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicle.getId());
                    if (vehicleOptional.isEmpty()) {
                        persistedVehicle = createAndPersistNewVehicle(vehicle);
                    } else {
                        persistedVehicle = vehicleOptional.get();
                    }
                } else {
                    persistedVehicle = createAndPersistNewVehicle(vehicle);
                }
                ParkingRecord parkingRecord = new ParkingRecord(inDate, persistedVehicle, parkingAreaOptional.get());
                ParkingRecord created = parkingRecordRepository.save(parkingRecord);
                return created;
            } else {
                throw ParkingAreaInsufficientCapacityException.createWith(parkingAreaId);
            }

        } else {
            throw ParkingAreaNotFoundException.createWith(parkingAreaId);
        }
    }

    private Vehicle createAndPersistNewVehicle(Vehicle vehicle) {
        Vehicle newVehicle = null;
        switch (vehicle.getType()) {
            case SUV:
                newVehicle = vehicleRepository.save((SUV) vehicle);
                break;
            case SEDAN:
                newVehicle = vehicleRepository.save((Sedan) vehicle);
                break;
            case MINIVAN:
                newVehicle = vehicleRepository.save((Minivan) vehicle);
                break;
        }
        return newVehicle;
    }

    private boolean isParkingAreaFull(ParkingArea parkingArea) {
        List<ParkingRecord> activeRecords = parkingRecordRepository.findParkingRecordByParkingActiveIsTrue();

        return activeRecords.stream().filter(e -> e.getParkingArea().equals(parkingArea)).count() >= parkingArea.getCapacity();
    }

    @Override
    public ParkingRecord checkOut(Long vehicleId) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
        if (vehicleOptional.isPresent()) {
            ParkingRecord activeParkingRecord = parkingRecordRepository.findByParkingActiveIsTrueAndVehicle(vehicleOptional.get());
            ParkingArea parkingArea = activeParkingRecord.getParkingArea();
            activeParkingRecord.setOutDate(clock.now());
            long hourDiff = ChronoUnit.HOURS.between(activeParkingRecord.getInDate(), activeParkingRecord.getOutDate());
            Optional<PriceData> priceData = parkingArea.getPriceList().stream().filter(e -> e.getStartHour() <= hourDiff && e.getEndHour() >= hourDiff).findAny();
            if (priceData.isPresent()) {
                double parkingFee = vehicleOptional.get().finalizeParkingFee(priceData.get().getPrice());
                activeParkingRecord.setFee(parkingFee);
                activeParkingRecord.setParkingActive(false);
                return parkingRecordRepository.save(activeParkingRecord);
            }
        }
        return null;
    }


}
