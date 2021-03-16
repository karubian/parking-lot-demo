package com.huawei.parkinglot.entity.parking;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
@Table
@Entity
@NoArgsConstructor
public class ParkingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ParkingRecord(@NotNull LocalDateTime inDate, Vehicle vehicle, ParkingArea parkingArea) {
        this.inDate = inDate;
        this.vehicle = vehicle;
        this.parkingArea = parkingArea;
        this.fee = 0;
        this.isParkingActive = true;
    }

    @NotNull
    private LocalDateTime inDate;

    private LocalDateTime outDate;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "vehicle_id",nullable = false)
    @JsonIgnore
    private Vehicle vehicle;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "parking_area_id",nullable = false)
    @JsonIgnore
    private ParkingArea parkingArea;

    private double fee;

    public boolean isParkingActive;
}
