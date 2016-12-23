package com.parking.Entity;

/**
 * Created by siddhahastmohapatra on 18/12/16.
 */

public class Car implements Item {

    private String registration;
    private String color;

    public Car(String registration, String color){
        this.registration = registration;
        this.color = color;
    }

    @Override
    public int compareTo(Item o) {
        if(this.registration.equalsIgnoreCase(o.getKey())){
            return 0;
        } else{
            return 1;
        }
    }

    @Override
    public String getKey() {
        return registration;
    }

    public String getColor(){
        return color;
    }

}
