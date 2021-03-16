package com.huawei.parkinglot.exception;

public class ParkingAreaNotFoundException extends RuntimeException {
    private Long id;
    private String name;

    public static ParkingAreaNotFoundException createWith(Long id) {
        return new ParkingAreaNotFoundException(id);
    }

    public static ParkingAreaNotFoundException createWith(String name) {
        return new ParkingAreaNotFoundException(name);
    }

    private ParkingAreaNotFoundException(Long id) {
        this.id = id;
    }

    private ParkingAreaNotFoundException(String name) {
        this.name = name;
    }

    @Override
    public String getMessage(){
        return name == null ? "ParkingArea with the id '" + id + "' not found" : "ParkingArea with the name '" + id + "' not found";
    }
}
