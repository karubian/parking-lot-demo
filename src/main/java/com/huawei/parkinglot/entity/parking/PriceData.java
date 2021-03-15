package com.huawei.parkinglot.entity.parking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table
@NoArgsConstructor
public class PriceData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public PriceData(int startHour, int endHour, int price ) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.price = price;
    }

    @NotNull
    @Min(0)
    @Max(24)
    private int startHour;

    @NotNull
    @Min(0)
    @Max(24)
    private int endHour;

    @NotNull
    private int price;


}
