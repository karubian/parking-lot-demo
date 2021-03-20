package com.burak.parkinglot.entity.vehicle;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Sedan extends Vehicle {

    public Sedan() {
        this.type = VehicleType.SEDAN;
    }

    @Override
    public double finalizeParkingFee(double parkingFee) {
        return parkingFee;
    }
}
