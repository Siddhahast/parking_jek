package com.parking.service;

import com.parking.Entity.Item;
import com.parking.Entity.ParkingSlot;
import com.parking.ParkingExceptions.ColoredItemUnavailable;
import com.parking.ParkingExceptions.DuplicateRegistration;
import com.parking.ParkingExceptions.ItemUnavailable;

/**
 * Created by siddhahastmohapatra on 24/12/16.
 */
public interface Reports {

    public Iterable getListOfSlotsFromColor(String color) throws ColoredItemUnavailable;

    public Integer getSlotFromRegistrationNumber(String registration) throws ItemUnavailable;

    public void addItem(Item item, ParkingSlot slot) throws DuplicateRegistration;

    public void removeItem(Item item, int slot_number) throws Exception;

    public Iterable getRegNumbersFromColor(String color) throws Exception;

    public Iterable getStatus();

}
