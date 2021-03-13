package com.huawei.parkinglot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table
public class ParkingArea {
	//TODO define attributes

    @Id
    private String name;

    private int capacity;

    private String city;

    @OneToMany(mappedBy = "parkingArea",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<PriceData> priceList;

}
