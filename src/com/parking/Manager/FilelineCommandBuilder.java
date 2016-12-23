package com.parking.Manager;

import com.parking.Entity.Command;
import com.parking.Utils.CommandConstants;
import com.parking.controller.ParkingController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by siddhahastmohapatra on 23/12/16.
 */
public class FilelineCommandBuilder extends CommandBuilder{

    private ParkingController parkingController ;
    private static BufferedWriter writer = null;
    private static final FilelineCommandBuilder builder = new FilelineCommandBuilder();

    private FilelineCommandBuilder(){

    }

    public static FilelineCommandBuilder getBuilder(String output){
        try {
            writer = new BufferedWriter(new FileWriter(output, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder;
    }

    public void commandsController(Command command){
        String command_name = command.getCommand();
        String[] parameters = command.getParamenters();
        try {
            switch (command_name){
                case CommandConstants.CREATE_PARKING_LOT:
                    parkingController = new ParkingController(Integer.parseInt(parameters[0]));
                    writer.append("Created parking lot with "+ parameters[0]+ " slots."+ "\n");
                    break;

                case CommandConstants.PARK:
                    writer.append(parkingController.parkItem(parameters[0], parameters[1])+"\n");
                    break;

                case CommandConstants.LEAVE:
                    writer.append(parkingController.leaveSlot(Integer.parseInt(parameters[0]))+"\n");
                    break;

                case CommandConstants.STATUS:
                    writer.append(parkingController.getStatus()+"\n");
                    break;

                case CommandConstants.REGISTRATION_NUMBERS_FROM_COLOR:
                    writer.append(parkingController.registrationNumbersFromColor(parameters[0])+"\n");
                    break;

                case CommandConstants.SLOT_NUMBERS_FROM_COLOR:
                    writer.append(parkingController.slotNumbersFromColor(parameters[0])+"\n");
                    break;

                case CommandConstants.SLOT_NUMBER_FROM_REGISTRATION_NUMBER:
                    writer.append(parkingController.getSlotNumberForRegistrationNumber(parameters[0])+"\n");
                    break;

            }
        } catch(Exception e){

        }


    }

    public void closeWriter(){
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
