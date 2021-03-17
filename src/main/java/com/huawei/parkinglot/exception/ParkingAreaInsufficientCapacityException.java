package com.huawei.parkinglot.exception;

public class ParkingAreaInsufficientCapacityException extends RuntimeException {
    private Long id;
    private String name;

    public static ParkingAreaInsufficientCapacityException createWith(Long id) {
        return new ParkingAreaInsufficientCapacityException(id);
    }

    public static ParkingAreaInsufficientCapacityException createWith(String name) {
        return new ParkingAreaInsufficientCapacityException(name);
    }

    private ParkingAreaInsufficientCapacityException(Long id) {
        this.id = id;
    }

    private ParkingAreaInsufficientCapacityException(String name) {
        this.name = name;
    }

    @Override
    public String getMessage(){
        return name == null ? "ParkingArea with the id '" + id + "' is full" : "ParkingArea with the name '" + id + "' is full";
    }
}
