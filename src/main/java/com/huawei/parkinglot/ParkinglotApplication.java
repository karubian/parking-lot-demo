package com.huawei.parkinglot;

import com.huawei.parkinglot.entity.ParkingArea;
import com.huawei.parkinglot.entity.PriceData;
import com.huawei.parkinglot.repository.ParkingAreaRepository;
import com.huawei.parkinglot.repository.PriceDataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class ParkinglotApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkinglotApplication.class, args);
    }

    @Bean
    public CommandLineRunner mappingDemo(ParkingAreaRepository parkingAreaRepository,
                                         PriceDataRepository priceDataRepository) {
        return args -> {
            ParkingArea parkingArea = new ParkingArea("Park", 15, "Ankara");
            parkingAreaRepository.save(parkingArea);

            priceDataRepository.save(new PriceData(1, 2, 15, parkingArea));
            priceDataRepository.save(new PriceData(2, 4, 20, parkingArea));

            List<PriceData> result = priceDataRepository.findByParkingArea(parkingArea, Sort.unsorted());
            System.out.println(result.size());

        };
    }
}
