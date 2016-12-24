package com.parking.Manager;

import com.parking.Entity.Item;
import com.parking.Entity.ParkingLot;
import com.parking.Entity.ParkingSlot;
import com.parking.ParkingExceptions.*;
import com.parking.service.*;

import java.util.*;

/**
 * Created by siddhahastmohapatra on 22/12/16.
 */
public class ParkingLotManager implements ParkingManager{

    private boolean[] slotsStatus;
    private String manager_name;

    private ParkingLot parkingLot;

    private Queue<ParkingSlot> availableSlots;
    private Queue<ParkingSlot> unavailableSlots;
    private Map<String, List<ParkingSlot>> colorMap;
    private Map<Integer, ParkingSlot> itemSlotMap;
    private Map<String, ParkingSlot> keySlotMap;

    private Reports reportKeeper;
    private Finder parkingSlotFinder;
    private Valet valet;

    public ParkingLotManager(int capacity_managed){
        this.parkingLot = ParkingLot.createParkingLot(capacity_managed);
        this.slotsStatus = new boolean[capacity_managed+1];
        this.colorMap = new HashMap<String, List<ParkingSlot>>();
        this.itemSlotMap = new HashMap<Integer, ParkingSlot>();
        this.keySlotMap = new HashMap<String, ParkingSlot>();//registration- slot map

        this.reportKeeper = new ReportsImpl(parkingLot);
        this.parkingSlotFinder = new FreeParkingSlotFinder(parkingLot);
        this.valet = new ValetImpl(parkingLot, reportKeeper);
    }

    public String parkItem(Item item) throws Exception {
        ParkingSlot slot = null;
        try{
            slot = parkingSlotFinder.findFreeParkingSlot();
            valet.parkItemInSlot(item, slot);
            reportKeeper.addItem(item, slot);
        } catch(Exception ex){
            return ex.getMessage();
        }
        return "Alloted Slot Number: "+slot.getSlot_number();

    }

    public String checkoutVehicleFromLot(int slot_number) throws Exception {
        try{
            ParkingSlot slot = valet.unparkItemInSlot(slot_number);
            parkingSlotFinder.addFreeSlot(slot);
        } catch (Exception ex){
            return ex.getMessage();
        }
        return "Slot Number "+slot_number+" is free.";
    }

    public Integer getSlotNumberForRegistrationNumber(String key) throws Exception {
        return reportKeeper.getSlotFromRegistrationNumber(key);
    }


    public Iterable<Integer> getSlotNumbersFromColor(String color) throws Exception{
        return reportKeeper.getListOfSlotsFromColor(color);
    }

    public Iterable<String> getRegNumbersFromColor(String color) throws Exception{
        List<Integer> slots = (List<Integer>) reportKeeper.getListOfSlotsFromColor(color);
        List<String> registrationNumbers = new ArrayList<String>();
        for(int slot:slots){
            registrationNumbers.add(parkingLot.getSlot(slot).getItem().getKey());
        }
        return registrationNumbers;
    }

    public int getItemsFromColor(String color){
        if(colorMap.get(color)!=null){
            return colorMap.get(color).size();
        } else{
            return 0;
        }
    }

    public Iterable<ParkingSlot> getStatus(){
        return reportKeeper.getStatus();
    }

}
