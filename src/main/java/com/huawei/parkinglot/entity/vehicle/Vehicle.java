package com.huawei.parkinglot.entity.vehicle;

import com.huawei.parkinglot.entity.parking.ParkingRecord;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String licensePlate;

    @NotNull
    private VehicleType type;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ParkingRecord> parkingRecords;

    @Transient
    private ParkingRecord activeParkingRecord;

    public abstract double finalizeParkingFee(double parkingFee);

}
