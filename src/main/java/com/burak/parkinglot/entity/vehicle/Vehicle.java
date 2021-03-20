package com.burak.parkinglot.entity.vehicle;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.burak.parkinglot.entity.parking.ParkingRecord;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SUV.class, name = "SUV"),
        @JsonSubTypes.Type(value = Minivan.class, name = "MINIVAN"),
        @JsonSubTypes.Type(value = Sedan.class, name = "SEDAN")})
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull
    @Column(unique = true)
    protected String licensePlate;

    @NotNull
    protected VehicleType type;

    @OneToMany(mappedBy = "vehicle",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<ParkingRecord> parkingRecords;

    public abstract double finalizeParkingFee(double parkingFee);

}
