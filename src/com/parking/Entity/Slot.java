package com.parking.Entity;

/**
 * Created by siddhahastmohapatra on 18/12/16.
 */
public class Slot implements Comparable<Slot>, ParkingSlot {

    private Item item;
    private int slot_number;
    private boolean isAvailable;

    public Slot(int slot_number){
        this.isAvailable = true;
        this.slot_number = slot_number;
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    public void setItem(Item item){
        this.isAvailable = false;
        this.item = item;
    }

    public Item getItem(){
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Slot slot = (Slot) o;

        return slot_number == slot.slot_number;

    }

    @Override
    public int hashCode() {
        return slot_number;
    }

    public int getSlot_number(){
        return slot_number;
    }

    @Override
    public int compareTo(Slot o) {
        if(this.slot_number==o.slot_number){
            return 0;
        }else if(this.slot_number>o.slot_number){
            return 1;
        } else{
            return -1;
        }
    }
}
