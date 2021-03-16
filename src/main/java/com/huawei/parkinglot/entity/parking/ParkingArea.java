package com.huawei.parkinglot.entity.parking;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Table
@Entity
@NoArgsConstructor
public class ParkingArea {

    public ParkingArea(String name, int capacity, String city) {
        this.name = name;
        this.capacity = capacity;
        this.city = city;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String name;

    @NotNull
    private int capacity;


    @NotNull
    private String city;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<PriceData> priceList;

    @OneToMany(mappedBy = "parkingArea",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<ParkingRecord> parkingRecords;


}
