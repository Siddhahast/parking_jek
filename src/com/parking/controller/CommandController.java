package com.parking.controller;

import com.parking.Entity.Command;

/**
 * Created by siddhahastmohapatra on 23/12/16.
 */
public class CommandController {

    private ParkingController parkingController;
    private static CommandController controller = null;

    private CommandController(ParkingController parkingController){
        this.parkingController = parkingController;
    }

    public static CommandController getController(ParkingController parkingController){
        if(parkingController==null){
            return new CommandController(parkingController);
        }
        return controller;
    }

    public void executeCommands(Command command){
        String command_message = command.getCommand();
        String[] parameters = command.getParamenters();
    }

}
