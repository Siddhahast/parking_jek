package com.parking.Manager;

import com.parking.Entity.Command;
import com.parking.Utils.CommandConstants;
import com.parking.controller.ParkingController;

/**
 * Created by siddhahastmohapatra on 23/12/16.
 */
public class IteractiveCommandBuilder extends CommandBuilder {

    private ParkingController parkingController ;
    private static final IteractiveCommandBuilder builder = new IteractiveCommandBuilder();

    public IteractiveCommandBuilder(){

    }

    public static IteractiveCommandBuilder getBuilder(){return builder;}

    public void commandsController(Command command){
        String command_name = command.getCommand();
        String[] parameters = command.getParamenters();
        switch (command_name){
            case CommandConstants.CREATE_PARKING_LOT:
                parkingController = new ParkingController(Integer.parseInt(parameters[0]));
                System.out.println("Created parking lot with "+ parameters[0]+ " slots.");
                break;

            case CommandConstants.PARK:
                System.out.println(parkingController.parkItem(parameters[0], parameters[1]));
                break;

            case CommandConstants.LEAVE:
                System.out.println(parkingController.leaveSlot(Integer.parseInt(parameters[0])));
                break;

            case CommandConstants.STATUS:
                System.out.println(parkingController.getStatus());
                break;

            case CommandConstants.REGISTRATION_NUMBERS_FROM_COLOR:
                System.out.println(parkingController.registrationNumbersFromColor(parameters[0]));
                break;

            case CommandConstants.SLOT_NUMBERS_FROM_COLOR:
                System.out.println(parkingController.slotNumbersFromColor(parameters[0]));
                break;

            case CommandConstants.SLOT_NUMBER_FROM_REGISTRATION_NUMBER:
                System.out.println(parkingController.getSlotNumberForRegistrationNumber(parameters[0]));
                break;

        }

    }

    @Override
    public void closeWriter() {

    }

}
