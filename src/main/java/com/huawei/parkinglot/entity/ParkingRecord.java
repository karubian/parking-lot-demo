package com.huawei.parkinglot.entity;


import com.huawei.parkinglot.entity.vehicle.Vehicle;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Table
@Entity
@NoArgsConstructor
public class ParkingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime localDateTime;

    @OneToOne(mappedBy = "parkingRecord")
    private Vehicle vehicle;
}
