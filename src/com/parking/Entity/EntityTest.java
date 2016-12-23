package com.parking.Entity;

/**
 * Created by siddhahastmohapatra on 22/12/16.
 */
public class EntityTest {

    public static void main(String[] args) {
        ParkingLot lot1 = ParkingLot.createParkingLot(6);
        ParkingLot lot2 = ParkingLot.createParkingLot(6);
        System.out.println(lot1.hashCode());
        System.out.println(lot2.hashCode());
        ParkingLot lot3 = ParkingLot.createParkingLot(6);
        System.out.println(lot3.hashCode());
    }
}
