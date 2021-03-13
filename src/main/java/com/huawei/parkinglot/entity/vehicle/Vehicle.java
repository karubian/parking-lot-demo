package com.huawei.parkinglot.entity.vehicle;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "vehicle")
//TODO define inheritance
public class Vehicle {

	@Id
	private String licensePlate;
	
	//TODO getters setters
}
