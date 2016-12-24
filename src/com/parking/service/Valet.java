package com.parking.service;


import com.parking.Entity.ParkingSlot;
import com.parking.Entity.Item;

/**
 * Created by siddhahastmohapatra on 24/12/16.
 */
public interface Valet {

    public void parkItemInSlot(Item item, ParkingSlot slot);

    public ParkingSlot unparkItemInSlot(int slot_number) throws Exception;

}
