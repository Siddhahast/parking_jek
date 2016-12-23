package com.parking.ParkingExceptions;

/**
 * Created by siddhahastmohapatra on 22/12/16.
 */
public class ItemUnavailable extends Exception {

    private String error_message;
    private int error_code = ExceptionCodeConstants.ITEM_UNAVAILABLE_CODE ;

    public ItemUnavailable(String message){
        this.error_message = message;
        this.error_code = ExceptionCodeConstants.COLORED_ITEM_UNAVAILABLE_CODE;
    }

    public String getMessage(){
        return error_message;
    }
}
