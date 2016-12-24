package com.parking.service;

import com.parking.Entity.ParkingSlot;

/**
 * Created by siddhahastmohapatra on 24/12/16.
 */
public interface Finder {

    public ParkingSlot findFreeParkingSlot() throws Exception;

    public void addFreeSlot(ParkingSlot slot);
}
