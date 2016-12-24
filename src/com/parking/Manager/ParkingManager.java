package com.parking.Manager;


import com.parking.Entity.Item;

/**
 * Created by siddhahastmohapatra on 22/12/16.
 */
public interface ParkingManager  {

    public Integer getSlotNumberForRegistrationNumber(String key) throws Exception;

    public Iterable getSlotNumbersFromColor(String color) throws Exception;

    public Iterable getRegNumbersFromColor(String color) throws Exception;

    public String parkItem(Item item) throws Exception;

    public String checkoutVehicleFromLot(int slot_number) throws Exception;

    public int getItemsFromColor(String color);

    public Iterable getStatus();

}
