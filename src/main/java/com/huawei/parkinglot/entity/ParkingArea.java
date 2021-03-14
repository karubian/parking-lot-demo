package com.huawei.parkinglot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private int capacity;

    private String city;

    @OneToMany(mappedBy = "parkingArea",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<PriceData> priceList;



}
