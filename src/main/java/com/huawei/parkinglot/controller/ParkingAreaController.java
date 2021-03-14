package com.huawei.parkinglot.controller;

import com.huawei.parkinglot.dto.ParkingRecordDto;
import com.huawei.parkinglot.entity.ParkingArea;
import com.huawei.parkinglot.service.ParkingAreaService;
import com.huawei.parkinglot.service.ParkingRecordService;
import com.huawei.parkinglot.validation.ParkingAreaValidator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ParkingAreaController {

    ParkingAreaService parkingAreaService;
    ParkingAreaValidator parkingAreaValidator;
    ParkingRecordService parkingRecordService;

    public ParkingAreaController(ParkingAreaService parkingAreaService, ParkingAreaValidator parkingAreaValidator, ParkingRecordService parkingRecordService) {
        this.parkingAreaService = parkingAreaService;
        this.parkingAreaValidator = parkingAreaValidator;
        this.parkingRecordService = parkingRecordService;
    }

    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(parkingAreaValidator);
    }

    @PostMapping(value = "/parkingArea", consumes = "application/json", headers = "content-type=application/json")
    public  String addParkingArea(@Valid @RequestBody ParkingArea parkingArea, BindingResult bindingResult) {
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

        parkingAreaService.updateParkingArea(parkingArea,id);
        return "success";

    }

    @DeleteMapping(value = "/parkingArea/{id}", consumes = "application/json", headers = "content-type=application/json")
    public String deleteParkingArea(@PathVariable Long id) {

        parkingAreaService.deleteParkingArea(id);
        return "success";

    }

    @PostMapping(value = "/checkIn", consumes = "application/json", headers = "content-type=application/json")
    public String checkIn(@Valid @RequestBody ParkingRecordDto parkingRecordDto) {
        parkingRecordService.checkIn(parkingRecordDto);
    }
}
