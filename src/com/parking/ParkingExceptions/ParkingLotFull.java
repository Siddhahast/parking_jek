package com.parking.ParkingExceptions;

import com.parking.Entity.ParkingLot;

/**
 * Created by siddhahastmohapatra on 22/12/16.
 */
public class ParkingLotFull extends Exception {

    private String error_message;
    private int error_code;

    public ParkingLotFull(String message){
        this.error_code = ExceptionCodeConstants.PARKING_LOT_FULL_CODE;
        this.error_message = message;
    }

    public String getMessage(){
        return error_message;
    }

}
