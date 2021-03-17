package com.huawei.parkinglot.service;

import com.huawei.parkinglot.entity.parking.ParkingArea;
import com.huawei.parkinglot.entity.parking.PriceData;
import com.huawei.parkinglot.exception.ParkingAreaNotFoundException;
import com.huawei.parkinglot.repository.ParkingAreaRepository;
import com.huawei.parkinglot.repository.PriceDataRepository;
import com.huawei.parkinglot.service.parking.ParkingAreaService;
import com.huawei.parkinglot.service.parking.ParkingAreaServiceImpl;
import com.huawei.parkinglot.service.parking.ParkingRecordService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParkingAreaServiceTest {

    @Mock
    private ParkingAreaRepository parkingAreaRepository;

    @Mock
    private PriceDataRepository priceDataRepository;

    @InjectMocks
    private final ParkingAreaService parkingAreaService = new ParkingAreaServiceImpl(parkingAreaRepository, priceDataRepository);
    @Mock
    private ParkingRecordService parkingRecordService;

    private ParkingArea testParkingArea;

    @Before
    public void setUp() {
        testParkingArea = new ParkingArea();
        List<PriceData> priceList = new ArrayList<>();
        priceList.add(new PriceData(0, 2, 10));
        priceList.add(new PriceData(2, 4, 10));
        priceList.add(new PriceData(4, 8, 10));
        priceList.add(new PriceData(8, 14, 10));
        priceList.add(new PriceData(14, 24, 10));

        testParkingArea.setPriceList(priceList);
        testParkingArea.setName("TestParkingArea");
        testParkingArea.setId(0l);
    }

    @Test
    public void whenSaveParkingArea_shouldReturnParkingArea() {
        ParkingArea parkingArea = testParkingArea;

        when(parkingAreaRepository.save(ArgumentMatchers.any(ParkingArea.class))).thenReturn(parkingArea);
        ParkingArea created = parkingAreaService.createParkingArea(parkingArea);
        assertThat(created.getName()).isSameAs(parkingArea.getName());

        verify(parkingAreaRepository).save(parkingArea);

    }

    @Test
    public void whenUpdateParkingArea_shouldReturnUpdatedParkingArea() {
        ParkingArea parkingArea = testParkingArea;


        when(parkingAreaRepository.save(ArgumentMatchers.any(ParkingArea.class))).thenReturn(parkingArea);
        when(parkingAreaRepository.findById(parkingArea.getId())).thenReturn(Optional.of(parkingArea));
        ParkingArea created = parkingAreaService.createParkingArea(parkingArea);

        parkingArea.setName("UpdatedParkingArea");

        ParkingArea updated = parkingAreaService.updateParkingArea(parkingArea, created.getId());

        assertThat(updated.getName()).isSameAs(parkingArea.getName());

    }

    @Test
    public void whenGivenId_shouldDeleteParkingArea_ifFound() {
        ParkingArea parkingArea = testParkingArea;

        when(parkingAreaRepository.findById(parkingArea.getId())).thenReturn(Optional.of(parkingArea));

        parkingAreaService.deleteParkingArea(parkingArea.getId());

        verify(parkingAreaRepository).delete(parkingArea);


    }

    @Test
    public void whenGivenName_returnParkingArea_ifFound() {
        ParkingArea parkingArea = testParkingArea;

        when(parkingAreaRepository.findParkingAreaByName(parkingArea.getName())).thenReturn(Optional.of(parkingArea));
        when(parkingAreaRepository.save(ArgumentMatchers.any(ParkingArea.class))).thenReturn(parkingArea);

        ParkingArea created = parkingAreaService.createParkingArea(parkingArea);
        assertThat(parkingAreaService.getParkingAreaByName(parkingArea.getName())).isSameAs(created);

        verify(parkingAreaRepository).findParkingAreaByName(parkingArea.getName());


    }


}
