package com.parking.Manager;

import com.parking.Entity.Item;
import com.parking.Entity.ParkingLot;
import com.parking.Entity.ParkingSlot;
import com.parking.ParkingExceptions.*;

import java.util.*;

/**
 * Created by siddhahastmohapatra on 22/12/16.
 */
public class ParkingLotManager implements ParkingManager{

    private boolean[] slotsStatus;
    private String manager_name;

    private ParkingLot parkingLot;
    private Map<String, List<ParkingSlot>> colorMap;
    private Map<Integer, ParkingSlot> itemSlotMap;
    private Map<String, ParkingSlot> keySlotMap;

    public ParkingLotManager(int capacity_managed){
        this.parkingLot = ParkingLot.createParkingLot(capacity_managed);
        this.slotsStatus = new boolean[capacity_managed+1];
        this.colorMap = new HashMap<String, List<ParkingSlot>>();
        this.itemSlotMap = new HashMap<Integer, ParkingSlot>();
        this.keySlotMap = new HashMap<String, ParkingSlot>();//registration- slot map
    }

    public int parkItem(Item item) throws Exception {
        /*
        -- check whether the slot is free?
        -- if free then park the vehicle - set the item there in the slot, mark the slot array in the parking lot
         */
        ParkingSlot slot = null;
        if(parkingLot.isParkingFull()){
            throw new ParkingLotFull(ExceptionCodeConstants.PARKING_LOT_FULL_MESSAGE);
        } else if(keySlotMap.get(item.getKey())!=null){
            throw new DuplicateRegistration(ExceptionCodeConstants.DUPLICATE_REGISTRATION_MESSAGE);
        }
        else{
            slot = parkingLot.getUnparkedSlot();
            int slot_number = slot.getSlot_number();
            slotsStatus[slot_number] = true;
            slot.setItem(item);
            updateColorVehiclesMap(item.getColor(), slot, true);
            itemSlotMap.put(slot_number, slot);
            keySlotMap.put(item.getKey(), slot);
            parkingLot.setItemInSlot(slot);
            return slot_number;
        }

    }

    /*
    Leave the slot number. Slot is emptied.
     */
    public void checkoutVehicleFromLot(int slot_number) throws Exception {
        if(!slotsStatus[slot_number]){
            throw new SlotEmptyException(ExceptionCodeConstants.PARKING_SLOT_IS_EMPTY_MESSAGE);
        } else{
            slotsStatus[slot_number] = false;
            ParkingSlot slot = itemSlotMap.get(slot_number);
            keySlotMap.remove(slot.getItem().getKey());
            updateColorVehiclesMap(slot.getItem().getColor(), slot, false);
            parkingLot.unsetItemInSlot(slot);
        }
    }

    private void updateColorVehiclesMap(String color, ParkingSlot slot, boolean parking) throws Exception {
        if(parking){
            if(colorMap.get(color)==null){
                List<ParkingSlot> color_slots_list = new ArrayList<ParkingSlot>();
                color_slots_list.add(slot);
                colorMap.put(color, color_slots_list);
            } else{
                List<ParkingSlot> color_slots_list = colorMap.get(color);
                color_slots_list.add(slot);
                colorMap.put(color, color_slots_list);
            }
        } else{
            if(colorMap.get(color)==null){
                throw new ColoredItemUnavailable(ExceptionCodeConstants.COLORED_ITEM_UNAVAILABLE_MESSAGE);
            } else{
                List<ParkingSlot> color_slots_list = colorMap.get(color);
                color_slots_list.remove(slot);
            }
        }
    }

    public int getSlotNumberForRegistrationNumber(String key) throws Exception {
        int slot_number = -1;
        ParkingSlot slot = keySlotMap.get(key);
        if(slot!=null){
            slot_number = slot.getSlot_number();
        } else{
            throw new ItemUnavailable(ExceptionCodeConstants.ITEM_UNAVAILABLE_MESSAGE);
        }
        return slot_number;
    }


    public Iterable<Integer> getSlotNumbersFromColor(String color) throws Exception{
        List<ParkingSlot> list = null;
        List<Integer> slotsList = null;
        if(colorMap.get(color)!=null){
            list = colorMap.get(color);
            slotsList = new ArrayList<Integer>();
            for(ParkingSlot slot:list){
                slotsList.add(slot.getSlot_number());
            }
        } else{
            //throw an exception
            throw new ItemUnavailable(ExceptionCodeConstants.ITEM_UNAVAILABLE_MESSAGE);
        }
        return slotsList;
    }

    public Iterable<String> getRegNumbersFromColor(String color) throws Exception{
        List<String> list = null;
        List<Integer> slotsList = null;
        if(colorMap.get(color)!=null){
            list = new ArrayList<String>();
            slotsList = new ArrayList<Integer>();
            List<ParkingSlot> slots = colorMap.get(color);
            for(ParkingSlot slot:slots){
                list.add(slot.getItem().getKey());
            }
        } else{
            throw new ColoredItemUnavailable(ExceptionCodeConstants.COLORED_ITEM_UNAVAILABLE_MESSAGE);
        }
        return list;
    }

    public int getItemsFromColor(String color){
        if(colorMap.get(color)!=null){
            return colorMap.get(color).size();
        } else{
            return 0;
        }
    }

    public Iterable<ParkingSlot> getStatus(){
        List<ParkingSlot> slots = new ArrayList<ParkingSlot>();
        List<ParkingSlot> parkingQueue = parkingLot.getParkedSlots();
        Iterator<ParkingSlot> it = parkingQueue.iterator();

        while(it.hasNext()){
            slots.add(it.next());
        }

        return slots;
    }

}
