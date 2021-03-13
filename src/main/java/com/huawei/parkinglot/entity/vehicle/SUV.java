package com.huawei.parkinglot.entity.vehicle;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "vehicle")
//TODO define inheritance
public class SUV extends Vehicle {

}
