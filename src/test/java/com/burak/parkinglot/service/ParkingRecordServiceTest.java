package com.burak.parkinglot.service;


import com.burak.parkinglot.entity.parking.ParkingArea;
import com.burak.parkinglot.entity.parking.ParkingRecord;
import com.burak.parkinglot.entity.parking.PriceData;
import com.burak.parkinglot.entity.vehicle.SUV;
import com.burak.parkinglot.entity.vehicle.Vehicle;
import com.burak.parkinglot.repository.ParkingAreaRepository;
import com.burak.parkinglot.repository.ParkingRecordRepository;
import com.burak.parkinglot.repository.VehicleRepository;
import com.burak.parkinglot.service.parking.ParkingRecordService;
import com.burak.parkinglot.service.parking.ParkingRecordServiceImpl;
import com.burak.parkinglot.util.TimeFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParkingRecordServiceTest {

    @Mock
    private ParkingAreaRepository parkingAreaRepository;


    @Mock
    private ParkingRecordRepository parkingRecordRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private TimeFactory clock;

    @InjectMocks
    private final ParkingRecordService parkingRecordService = new ParkingRecordServiceImpl(parkingAreaRepository, vehicleRepository, parkingRecordRepository, clock);

    @Test
    public void whenCheckIn_shouldReturnNewParkingRecord() {
        ParkingArea testParkingArea = new ParkingArea();
        List<PriceData> priceList = new ArrayList<>();
        priceList.add(new PriceData(0, 2, 10));
        priceList.add(new PriceData(2, 4, 10));
        priceList.add(new PriceData(4, 8, 10));
        priceList.add(new PriceData(8, 14, 10));
        priceList.add(new PriceData(14, 24, 10));

        testParkingArea.setPriceList(priceList);
        testParkingArea.setName("TestParkingArea");
        testParkingArea.setCapacity(20);
        testParkingArea.setId(0L);

        Vehicle testVehicle = new SUV();
        testVehicle.setLicensePlate("06BOB");
        testVehicle.setId(0L);

        ParkingRecord parkingRecord = new ParkingRecord();
        parkingRecord.setId(0L);
        parkingRecord.setVehicle(testVehicle);

        LocalDateTime checkInTime = LocalDateTime.of(2005, 1, 1, 1, 1);
        parkingRecord.setInDate(checkInTime);
        parkingRecord.setParkingArea(testParkingArea);

        when(parkingRecordRepository.save(ArgumentMatchers.any(ParkingRecord.class))).thenReturn(parkingRecord);
        when(parkingRecordRepository.findParkingRecordByParkingActiveIsTrue()).thenReturn(new ArrayList<>());
        when(parkingAreaRepository.findById(testParkingArea.getId())).thenReturn(Optional.of(testParkingArea));
        when(vehicleRepository.findById(testVehicle.getId())).thenReturn(Optional.of(testVehicle));


        ParkingRecord created = parkingRecordService.checkIn(checkInTime, testVehicle, testParkingArea.getId());
        assertThat(created.getId()).isSameAs(parkingRecord.getId());
        assertThat(created.getParkingArea()).isSameAs(testParkingArea);
        assertThat(created.getVehicle()).isSameAs(testVehicle);
        assertThat(created.getInDate()).isSameAs(checkInTime);


    }

    @Test
    public void whenCheckOut_shouldUpdateParkingRecord() {
        ParkingArea testParkingArea = new ParkingArea();
        List<PriceData> priceList = new ArrayList<>();
        priceList.add(new PriceData(0, 2, 10));
        priceList.add(new PriceData(2, 4, 15));
        priceList.add(new PriceData(4, 8, 20));
        priceList.add(new PriceData(8, 14, 25));
        priceList.add(new PriceData(14, 24, 30));

        testParkingArea.setPriceList(priceList);
        testParkingArea.setName("TestParkingArea");
        testParkingArea.setCapacity(20);
        testParkingArea.setId(0L);

        Vehicle testVehicle = new SUV();
        testVehicle.setLicensePlate("06BOB");
        testVehicle.setId(0L);

        ParkingRecord parkingRecord = new ParkingRecord();
        parkingRecord.setId(0L);
        parkingRecord.setVehicle(testVehicle);

        LocalDateTime checkInTime = LocalDateTime.of(2005, 1, 1, 1, 1, 1).minusHours(6);
        parkingRecord.setInDate(checkInTime);
        parkingRecord.setParkingArea(testParkingArea);

        parkingRecord.setOutDate(checkInTime.plusHours(6));

        when(parkingRecordRepository.save(ArgumentMatchers.any(ParkingRecord.class))).thenReturn(parkingRecord);
        when(vehicleRepository.findById(testVehicle.getId())).thenReturn(Optional.of(testVehicle));
        when(parkingRecordRepository.findByParkingActiveIsTrueAndVehicle(testVehicle)).thenReturn(parkingRecord);
        when(clock.now()).thenReturn(LocalDateTime.of(2005, 1, 1, 1, 1, 1));
        ParkingRecord updated = parkingRecordService.checkOut(testVehicle.getId());
        //assertThat(updated.getOutDate()).isSameAs(parkingRecord.getInDate().plusHours(6));
        assertThat(updated.isParkingActive()).isEqualTo(false);
        assertThat(updated.getFee()).isEqualTo(20 / 100.0 * 110);

    }

}
