package com.parking.Entity;

/**
 * Created by siddhahastmohapatra on 22/12/16.
 */
public interface ParkingSlot {

    public Item getItem();

    public int getSlot_number();

    public boolean isAvailable();

    public void setItem(Item item);

    public void setAvailable(boolean available);
}
