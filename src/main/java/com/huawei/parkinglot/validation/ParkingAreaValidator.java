package com.huawei.parkinglot.validation;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import com.huawei.parkinglot.entity.ParkingArea;
import com.huawei.parkinglot.repository.PriceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ParkingAreaValidator implements Validator {

    @Autowired
    PriceDataRepository priceDataRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return ParkingArea.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ParkingArea parkingArea = (ParkingArea) o;
        RangeSet<Integer> rangeSet = TreeRangeSet.create();

        parkingArea.getPriceList().stream().forEach(e -> {
            rangeSet.add(Range.openClosed(e.getStartHour(),e.getEndHour()));
        });
        Range<Integer> hourRange = Range.open(0,24);

        if(!(rangeSet.span().lowerEndpoint() >= 0 && rangeSet.span().upperEndpoint() <= 24)) {
            errors.reject("Hour info must be between 0 and 24");
        } else if(!rangeSet.encloses(hourRange)) {
            errors.reject("All hour intervals from 0 to 24 must be defined.");
        }
    }
}
