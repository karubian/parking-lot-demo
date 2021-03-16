package com.huawei.parkinglot.entity.vehicle;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Minivan extends Vehicle {

    @Override
    public double finalizeParkingFee(double parkingFee) {
        return (parkingFee / 100.0) * 115;
    }
}
