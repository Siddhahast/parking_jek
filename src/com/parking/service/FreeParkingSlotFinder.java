package com.parking.service;

import com.parking.Entity.ParkingLot;
import com.parking.Entity.ParkingSlot;
import com.parking.ParkingExceptions.ExceptionCodeConstants;
import com.parking.ParkingExceptions.ParkingLotFull;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by siddhahastmohapatra on 24/12/16.
 */
public class FreeParkingSlotFinder implements Finder{

    private ParkingLot parkingLot;
    private Queue<ParkingSlot> unparkedSlots;

    public FreeParkingSlotFinder(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
        this.unparkedSlots = new PriorityQueue<ParkingSlot>();
        ParkingSlot[] slots = parkingLot.getParkingSlots();
        for(int i=1;i<slots.length;i++){
            if(slots[i].isAvailable()){
                unparkedSlots.add(slots[i]);
            }
        }
    }


    @Override
    public ParkingSlot findFreeParkingSlot() throws Exception{
        if(unparkedSlots.size()==0){
            throw new ParkingLotFull(ExceptionCodeConstants.PARKING_LOT_FULL_MESSAGE);
        } else{
            return unparkedSlots.poll();
        }
    }

    public void addFreeSlot(ParkingSlot slot){
        this.unparkedSlots.add(slot);
    }

}
