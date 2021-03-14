package com.huawei.parkinglot.controller;

import com.huawei.parkinglot.entity.ParkingArea;
import com.huawei.parkinglot.repository.PriceDataRepository;
import com.huawei.parkinglot.service.ParkingAreaService;
import com.huawei.parkinglot.validation.ParkingAreaValidator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ParkingAreaController {

    ParkingAreaService parkingAreaService;
    PriceDataRepository priceDataRepository;
    ParkingAreaValidator parkingAreaValidator;

    public ParkingAreaController(ParkingAreaService parkingAreaService, ParkingAreaValidator parkingAreaValidator, PriceDataRepository priceDataRepository) {
        this.parkingAreaService = parkingAreaService;
        this.parkingAreaValidator = parkingAreaValidator;
        this.priceDataRepository = priceDataRepository;
    }

    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(parkingAreaValidator);
    }

    @PostMapping(value = "/parkingArea", consumes = "application/json", headers = "content-type=application/json")
    String addParkingArea(@Valid @RequestBody ParkingArea parkingArea, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().get(0).getCode();
        }
     /*   parkingArea.getPriceList().get(0).setParkingArea(parkingArea);
        parkingAreaService.createParkingArea(parkingArea);*/
        return "success";

    }


}
