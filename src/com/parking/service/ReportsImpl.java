package com.parking.service;

import com.parking.Entity.Item;
import com.parking.Entity.ParkingLot;
import com.parking.Entity.ParkingSlot;
import com.parking.Entity.Slot;
import com.parking.ParkingExceptions.*;

import java.util.*;

/**
 * Created by siddhahastmohapatra on 24/12/16.
 */
public class ReportsImpl implements Reports {

    private ParkingLot parkingLot;
    private Map<String, List<Integer>> colorMap;
    private Map<Integer, ParkingSlot> itemSlotMap;
    private Map<String, Integer> keySlotMap;

    public ReportsImpl(ParkingLot parkingLot){
        colorMap = new HashMap<String, List<Integer>>();
        itemSlotMap = new HashMap<Integer, ParkingSlot>();
        keySlotMap = new HashMap<String, Integer>();
        this.parkingLot = parkingLot;
    }

    @Override
    public Iterable getListOfSlotsFromColor(String color) throws ColoredItemUnavailable {
        if(colorMap.get(color)==null){
            throw new ColoredItemUnavailable(ExceptionCodeConstants.COLORED_ITEM_UNAVAILABLE_MESSAGE);
        } else{
            return colorMap.get(color);
        }
    }

    @Override
    public Integer getSlotFromRegistrationNumber(String registration) throws ItemUnavailable {
        if(keySlotMap.get(registration)==null){
            throw new ItemUnavailable(ExceptionCodeConstants.ITEM_UNAVAILABLE_MESSAGE);
        }
        return keySlotMap.get(registration);
    }

    public void addItem(Item item, ParkingSlot slot) throws DuplicateRegistration {
        if(colorMap.get(item.getColor())==null){
            List<Integer> list = new LinkedList<Integer>();
            list.add(slot.getSlot_number());
            colorMap.put(item.getColor(), list);
        } else {
            colorMap.get(item.getColor()).add(slot.getSlot_number());
        }
        itemSlotMap.put(slot.getSlot_number(), slot);
        if(keySlotMap.get(item.getKey())==null){
            keySlotMap.put(item.getKey(), slot.getSlot_number());
        } else{
            throw new DuplicateRegistration(ExceptionCodeConstants.DUPLICATE_REGISTRATION_MESSAGE);
        }
    }

    public void removeItem(Item item, int slot_number) throws SlotEmptyException {
        String color = item.getColor();
        itemSlotMap.remove(slot_number);
        List<Integer> list = colorMap.get(color);
        list.remove(new Integer(slot_number));
        if(list.size()==0){
            colorMap.remove(color);
        } else{
            colorMap.put(color, list);
        }
        keySlotMap.remove(item.getKey());
    }

    @Override
    public Iterable getRegNumbersFromColor(String color) throws Exception {
        List<String> registrationNumbers = null;
        if(colorMap.get(color)==null){
            throw new ColoredItemUnavailable(ExceptionCodeConstants.COLORED_ITEM_UNAVAILABLE_MESSAGE);
        } else{
            List<Integer> parkedSlotsForColor = colorMap.get(color);
            registrationNumbers = new ArrayList<String>();
            for(int i:parkedSlotsForColor){
                registrationNumbers.add(parkingLot.getSlot(i).getItem().getKey());
            }
        }
        return registrationNumbers;
    }

    @Override
    public Iterable getStatus() {
        List<ParkingSlot> parkedSlotsInfo = new ArrayList<ParkingSlot>();
        for(Map.Entry<Integer, ParkingSlot> entry:itemSlotMap.entrySet()){
            parkedSlotsInfo.add(entry.getValue());
        }
        return parkedSlotsInfo;
    }

}
