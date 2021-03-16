package com.huawei.parkinglot.controller;

import com.huawei.parkinglot.entity.parking.ParkingArea;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import com.huawei.parkinglot.exception.ParkingAreaNotFoundException;
import com.huawei.parkinglot.service.parking.ParkingAreaService;
import com.huawei.parkinglot.service.parking.ParkingRecordService;
import com.huawei.parkinglot.validation.ParkingAreaValidator;
import io.swagger.models.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class ParkingAreaController {

    ParkingAreaService parkingAreaService;
    ParkingAreaValidator parkingAreaValidator;
    ParkingRecordService parkingRecordService;

    public ParkingAreaController(ParkingAreaService parkingAreaService, ParkingAreaValidator parkingAreaValidator, ParkingRecordService parkingRecordService/*VehicleService vehicleService*/) {
        this.parkingAreaService = parkingAreaService;
        this.parkingAreaValidator = parkingAreaValidator;
        this.parkingRecordService = parkingRecordService;
    }

    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(parkingAreaValidator);
    }

    @PostMapping(value = "/parkingArea", consumes = "application/json", headers = "content-type=application/json")
    public ParkingArea addParkingArea(@RequestBody ParkingArea parkingArea, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        return  parkingAreaService.createParkingArea(parkingArea);

    }

    @PutMapping(value = "/parkingArea/{id}", consumes = "application/json", headers = "content-type=application/json")
    public ParkingArea updateParkingArea(@Valid @RequestBody ParkingArea parkingArea, @PathVariable Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        return parkingAreaService.updateParkingArea(parkingArea, id);

    }

    @DeleteMapping(value = "/parkingArea/{id}", consumes = "application/json", headers = "content-type=application/json")
    public void deleteParkingArea(@PathVariable Long id) throws ParkingAreaNotFoundException {

        parkingAreaService.deleteParkingArea(id);

    }

    @PostMapping(value = "/checkIn/{parkingAreaId}", consumes = "application/json", headers = "content-type=application/json")
    public ResponseEntity<Object> checkIn(@RequestBody Vehicle vehicle, @PathVariable Long parkingAreaId) {
        parkingRecordService.checkIn(LocalDateTime.now(), vehicle, parkingAreaId);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/checkOut/{vehicleId}", consumes = "application/json", headers = "content-type=application/json")
    public ResponseEntity<Object> checkOut(@PathVariable Long vehicleId) {
        parkingRecordService.checkOut(vehicleId);

        return ResponseEntity.ok().build();
    }
}
