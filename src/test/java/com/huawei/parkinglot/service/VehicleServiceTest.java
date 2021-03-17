package com.huawei.parkinglot.service;

import com.huawei.parkinglot.entity.parking.ParkingArea;
import com.huawei.parkinglot.entity.parking.ParkingRecord;
import com.huawei.parkinglot.entity.vehicle.SUV;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import com.huawei.parkinglot.service.vehicle.VehicleService;
import com.huawei.parkinglot.service.vehicle.VehicleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService = new VehicleServiceImpl();

/*    @Test
    public void whenGetParkingDetails_shouldReturnParkingRecords() {
        Vehicle vehicle = new SUV();
        vehicle.setId(0L);
        vehicle.setLicensePlate("AA");

        List<ParkingRecord> parkingRecords = new ArrayList<>();
        ParkingRecord parkingRecord = new ParkingRecord();
        parkingRecords.add(parkingRecord);
        parkingRecord.setVehicle(vehicle);

        vehicle.setParkingRecords(parkingRecords);

        when(vehicleService.getParkingDetails(ArgumentMatchers.any(SUV.class))).thenReturn(parkingRecords);

        assertThat(vehicleService.getParkingDetails(vehicle)).isSameAs(vehicle.getParkingRecords());
    }*/
}
