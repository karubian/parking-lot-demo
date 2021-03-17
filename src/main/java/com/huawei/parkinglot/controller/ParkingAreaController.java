package com.huawei.parkinglot.controller;

import com.huawei.parkinglot.entity.parking.ParkingArea;
import com.huawei.parkinglot.service.parking.ParkingAreaService;
import com.huawei.parkinglot.service.parking.ParkingRecordService;
import com.huawei.parkinglot.validation.ParkingAreaValidator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

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

    @PostMapping(value = "/parkingAreas", consumes = "application/json", headers = "content-type=application/json")
    public ParkingArea addParkingArea(@RequestBody @Valid ParkingArea parkingArea, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Parking area information is not valid.");
        }
        return parkingAreaService.createParkingArea(parkingArea);

    }

    @PutMapping(value = "/parkingAreas/{id}", consumes = "application/json", headers = "content-type=application/json")
    public ParkingArea updateParkingArea(@Valid @RequestBody ParkingArea parkingArea, @PathVariable Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Parking area information is not valid.");
        }
        return parkingAreaService.updateParkingArea(parkingArea, id);

    }

    @DeleteMapping(value = "/parkingAreas/{id}", consumes = "application/json", headers = "content-type=application/json")
    public void deleteParkingArea(@PathVariable Long id) {

        parkingAreaService.deleteParkingArea(id);

    }


}
