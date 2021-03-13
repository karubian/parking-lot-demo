package com.huawei.parkinglot.entity;

import lombok.Data;
import org.springframework.data.util.Pair;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class PriceData {
    @Id
    private Long id;

    private Pair<Integer,Integer> hourRange;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "name",nullable = false)
    private ParkingArea parkingArea;


}
