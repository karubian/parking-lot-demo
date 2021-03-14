package com.huawei.parkinglot.entity.vehicle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huawei.parkinglot.entity.ParkingArea;
import com.huawei.parkinglot.entity.ParkingRecord;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vehicle")
//TODO define inheritance
public class Vehicle {

	@Id
	private String licensePlate;

	@OneToOne
	@JoinColumn(name = "parking_record_id",nullable = false)
	@JsonIgnore
	private ParkingRecord parkingRecord;
}
