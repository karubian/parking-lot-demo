package com.burak.parkinglot.entity.vehicle;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Minivan extends Vehicle {
    public Minivan() {
        this.type = VehicleType.MINIVAN;
    }

    @Override
    public double finalizeParkingFee(double parkingFee) {
        return (parkingFee / 100.0) * 115;
    }
}
