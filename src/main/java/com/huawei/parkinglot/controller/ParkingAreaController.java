package com.huawei.parkinglot.controller;

import com.huawei.parkinglot.entity.parking.ParkingArea;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import com.huawei.parkinglot.service.parking.ParkingAreaService;
import com.huawei.parkinglot.service.parking.ParkingRecordService;
import com.huawei.parkinglot.service.vehicle.VehicleService;
import com.huawei.parkinglot.validation.ParkingAreaValidator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
public class ParkingAreaController {

    ParkingAreaService parkingAreaService;
    ParkingAreaValidator parkingAreaValidator;
    ParkingRecordService parkingRecordService;
    VehicleService vehicleService;

    public ParkingAreaController(ParkingAreaService parkingAreaService, ParkingAreaValidator parkingAreaValidator, ParkingRecordService parkingRecordService,VehicleService vehicleService) {
        this.parkingAreaService = parkingAreaService;
        this.parkingAreaValidator = parkingAreaValidator;
        this.parkingRecordService = parkingRecordService;
        this.vehicleService = vehicleService;
    }

    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(parkingAreaValidator);
    }

    @PostMapping(value = "/parkingArea", consumes = "application/json", headers = "content-type=application/json")
    public String addParkingArea(@Valid @RequestBody ParkingArea parkingArea, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().get(0).getCode();
        }
        parkingAreaService.createParkingArea(parkingArea);

        return "success";

    }

    @PutMapping(value = "/parkingArea/{id}", consumes = "application/json", headers = "content-type=application/json")
    public String updateParkingArea(@Valid @RequestBody ParkingArea parkingArea, @PathVariable Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().get(0).getCode();
        }

        parkingAreaService.updateParkingArea(parkingArea, id);
        return "success";

    }

    @DeleteMapping(value = "/parkingArea/{id}", consumes = "application/json", headers = "content-type=application/json")
    public String deleteParkingArea(@PathVariable Long id) {

        parkingAreaService.deleteParkingArea(id);
        return "success";

    }

    @PostMapping(value = "/checkIn/{parkingAreaId}", consumes = "application/json", headers = "content-type=application/json")
    public String checkIn(@RequestBody @Valid Vehicle vehicle, @PathVariable Long parkingAreaId) {
        parkingRecordService.checkIn(LocalDateTime.now(), vehicle, parkingAreaId);

        return "a";
    }

    @PostMapping(value = "/checkOut/{vehicleId}", consumes = "application/json", headers = "content-type=application/json")
    public String checkOut(@PathVariable Long vehicleId) {
        //vehicleService.checkOut();

        return "a";
    }
}
