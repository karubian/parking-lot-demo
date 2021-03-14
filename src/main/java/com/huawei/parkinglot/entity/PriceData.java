package com.huawei.parkinglot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table
@NoArgsConstructor
public class PriceData {

    public PriceData(int startHour, int endHour, int price, ParkingArea parkingArea) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.price = price;
        this.parkingArea = parkingArea;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(0)
    @Max(24)
    private int startHour;

    @NotNull
    @Min(0)
    @Max(24)
    private int endHour;

    @NotNull
    private int price;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "parking_area_id",nullable = false)
    @JsonIgnore
    private ParkingArea parkingArea;


}
