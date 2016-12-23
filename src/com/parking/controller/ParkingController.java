package com.parking.controller;

import com.parking.Entity.Car;
import com.parking.Entity.Item;
import com.parking.Entity.ParkingSlot;
import com.parking.Manager.ParkingLotManager;
import com.parking.Manager.ParkingManager;
import com.parking.Utils.StringUtils;

import java.util.List;

/**
 * Created by siddhahastmohapatra on 22/12/16.
 */
public class ParkingController {

    private ParkingManager parkingManager;

    public ParkingController(int capacity){
        this.parkingManager = new ParkingLotManager(capacity);
    }

    public String parkItem(String registrationNumber, String color){
        Item item = new Car(registrationNumber, color);
        int slot_number = -1;
        try {
            slot_number = parkingManager.parkItem(item);
            return "Allocated slot number: "+ slot_number ;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String leaveSlot(int slot){
        try{
            parkingManager.checkoutVehicleFromLot(slot);
            return "Slot number "+slot+" is free";
        } catch(Exception e){
            return e.getMessage();
        }
    }

    public String getStatus(){
        return StringUtils.slotDetails((List<ParkingSlot>) parkingManager.getStatus());
    }

    public String registrationNumbersFromColor(String color){
        try {
            return StringUtils.createCommaStringEntries((List<String>) parkingManager.getRegNumbersFromColor(color));
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String slotNumbersFromColor(String color){
        try {
            return StringUtils.createCommaIntegerEntries((List<Integer>) parkingManager.getSlotNumbersFromColor(color));
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getSlotNumberForRegistrationNumber(String registrationNumber){
        try {
            return String.valueOf(parkingManager.getSlotNumberForRegistrationNumber(registrationNumber));
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void test(){
        System.out.println("Test Controller");
    }

}
