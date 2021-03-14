package com.huawei.parkinglot.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingRecordDto {
    private LocalDateTime localDateTime;
    private Long vehicleId;
    private Long parkingAreaId;
}
