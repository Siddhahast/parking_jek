package com.parking.ParkingExceptions;

/**
 * Created by siddhahastmohapatra on 22/12/16.
 */
public class SlotEmptyException extends Exception{

    private String error_message;
    private int error_code = ExceptionCodeConstants.PARKING_SLOT_IS_EMPTY_CODE;

    public SlotEmptyException(String message){
        this.error_message = message;
        this.error_code = ExceptionCodeConstants.PARKING_SLOT_IS_EMPTY_CODE;
    }

    public String getMessage(){
        return error_message;
    }

}
