package com.huawei.parkinglot.controller;

import com.huawei.parkinglot.entity.parking.ParkingRecord;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import com.huawei.parkinglot.service.parking.ParkingAreaService;
import com.huawei.parkinglot.service.parking.ParkingRecordService;
import com.huawei.parkinglot.validation.ParkingAreaValidator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ParkingRecordController {

    ParkingAreaService parkingAreaService;
    ParkingAreaValidator parkingAreaValidator;
    ParkingRecordService parkingRecordService;

    public ParkingRecordController(ParkingAreaService parkingAreaService, ParkingAreaValidator parkingAreaValidator, ParkingRecordService parkingRecordService/*VehicleService vehicleService*/) {
        this.parkingAreaService = parkingAreaService;
        this.parkingAreaValidator = parkingAreaValidator;
        this.parkingRecordService = parkingRecordService;
    }


    @PostMapping(value = "/checkIn/{parkingAreaId}", consumes = "application/json", headers = "content-type=application/json")
    public ParkingRecord checkIn(@RequestBody Vehicle vehicle, @PathVariable Long parkingAreaId) {
        return parkingRecordService.checkIn(LocalDateTime.now(), vehicle, parkingAreaId);


    }

    @PostMapping(value = "/checkOut/{vehicleId}", consumes = "application/json", headers = "content-type=application/json")
    public ParkingRecord checkOut(@PathVariable Long vehicleId) {
        return parkingRecordService.checkOut(vehicleId);

    }
}
