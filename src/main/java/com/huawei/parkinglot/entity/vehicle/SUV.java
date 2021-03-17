package com.huawei.parkinglot.entity.vehicle;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class SUV extends Vehicle {

    public SUV() {
        this.type = VehicleType.SUV;
    }
    @Override
    public double finalizeParkingFee(double parkingFee) {
        return (parkingFee / 100.0) * 110;
    }
}
