package com.parking.service;

import com.parking.Entity.Item;
import com.parking.Entity.ParkingLot;
import com.parking.Entity.ParkingSlot;
import com.parking.ParkingExceptions.SlotEmptyException;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by siddhahastmohapatra on 24/12/16.
 */
public class ValetImpl implements Valet{

    private ParkingLot parkingLot;
    private Queue<ParkingSlot> parkedSlots;
    private Reports keeper;

    public ValetImpl(ParkingLot parkingLot, Reports keeper){
        this.parkingLot = parkingLot;
        this.parkedSlots = new LinkedList<ParkingSlot>();
        this.keeper = keeper;
        ParkingSlot[] slots = parkingLot.getParkingSlots();

        for(int i =1;i<parkingLot.getCapacity();i++){
            if(!slots[i].isAvailable()){
                parkedSlots.add(slots[i]);
            }
        }
    }

    @Override
    public void parkItemInSlot(Item item, ParkingSlot slot) {
        //parkingLot.getParkedSlots().add(slot);
        parkingLot.setSlotAsParked(slot.getSlot_number(), slot);
        parkingLot.getSlot(slot.getSlot_number()).setItem(item);
        parkingLot.getSlot(slot.getSlot_number()).setAvailable(false);
        parkedSlots.add(slot);
    }

    @Override
    public ParkingSlot unparkItemInSlot(int slot_number) throws Exception{

        if(slot_number>parkingLot.getCapacity()){
            throw new IndexOutOfBoundsException();
        }
        if(parkingLot.getSlot(slot_number).isAvailable()){
            throw new SlotEmptyException("The slot is empty. No Car present");
        }
        ParkingSlot slot = parkingLot.getSlot(slot_number);
        Item itemTobeRemoved = slot.getItem();
        keeper.removeItem(itemTobeRemoved, slot_number);
        parkedSlots.remove(slot);
        slot.setItem(null);
        slot.setAvailable(true);
        parkingLot.setSlotAsUnparked(slot_number, slot);
        return slot;
    }

}
