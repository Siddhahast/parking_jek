package com.parking.Entity;

import java.util.*;

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
        this.capacity = num+1;
        unparkedSlots = new PriorityQueue<ParkingSlot>();
        parkedSlots = new LinkedList<ParkingSlot>();
        this.slots = new ParkingSlot[capacity];
        for(int i=1;i<capacity;i++){
            slots[i] = new Slot(i);
            unparkedSlots.add(slots[i]);
        }
    }

    public ParkingSlot[] getParkingSlots(){
        return slots;
    }

    public static ParkingLot createParkingLot(int capacity){
        if(INSTANCE==null){
            INSTANCE = new ParkingLot(capacity);
            return INSTANCE;
        } else{
            return INSTANCE;
        }
    }

    public void setSlotAsParked(int slot_number, ParkingSlot slot){
        slots[slot_number] = slot;
    }

    public void setSlotAsUnparked(int slot_number, ParkingSlot slot){
        slots[slot_number] = slot;
    }

    public static ParkingLot getInstance(){return INSTANCE;}


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

    public Queue<ParkingSlot> getUnparkedSlots(){return unparkedSlots;}

    public int getCapacity(){return capacity;}

}
