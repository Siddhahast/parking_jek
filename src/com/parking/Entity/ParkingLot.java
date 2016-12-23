package com.parking.Entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by siddhahastmohapatra on 22/12/16.
 */
public class ParkingLot {

    private ParkingSlot[] slots;
    private int capacity;
    private static ParkingLot INSTANCE = null;
    private Queue<ParkingSlot> unparkedSlots;
    private List<ParkingSlot> parkedSlots;

    private ParkingLot(int num){
        this.capacity = num;
        unparkedSlots = new LinkedList<ParkingSlot>();
        parkedSlots = new LinkedList<ParkingSlot>();
        this.slots = new ParkingSlot[num];
        for(int i=0;i<num;i++){
            slots[i] = new Slot(i+1);
            unparkedSlots.add(slots[i]);
        }
    }

    public static ParkingLot createParkingLot(int capacity){
        if(INSTANCE==null){
            INSTANCE = new ParkingLot(capacity);
            return INSTANCE;
        } else{
            return INSTANCE;
        }
    }

    public boolean getSlotStatus(int slot_number){
        return slots[slot_number].isAvailable();
    }

    public void setItemInSlot(ParkingSlot slot){
        parkedSlots.add(slot);
    }

    /*
    Set the availability to true and null registration id.
    The other object is set for grabage collection.
     */
    public void unsetItemInSlot(ParkingSlot slot){
        parkedSlots.remove(slot);
        slot.setItem(null);
        unparkedSlots.add(slot);
    }

    public ParkingSlot getSlot(int slot_number){
        return slots[slot_number];
    }

    public ParkingSlot getUnparkedSlot(){
        return unparkedSlots.poll();
    }

    public boolean isParkingFull(){
        return parkedSlots.size()==capacity;
    }

    public boolean isParkingEmpty(){
        return unparkedSlots.size() == capacity;
    }

    public void removeSlotFromParked(Slot slot){
        parkedSlots.remove(slot);
    }

    public void removeSlotFromUnparked(Slot slot){
        unparkedSlots.remove(slot);
    }

    public List<ParkingSlot> getParkedSlots(){
        return parkedSlots;
    }

}
