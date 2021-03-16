package com.huawei.parkinglot.entity.vehicle;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
public class Sedan extends Vehicle {

    @Override
    public double finalizeParkingFee(double parkingFee) {
        return parkingFee;
    }
}
