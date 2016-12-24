package com.parking.Manager;

import java.util.List;
import java.util.ArrayList;

import com.parking.Entity.Command;
import com.parking.Utils.CommandConstants;
import com.parking.controller.ParkingController;

/**
 * Created by siddhahastmohapatra on 23/12/16.
 */
public abstract class CommandBuilder {

    private ParkingController parkingController ;

    public abstract void commandsController(Command build);

    public abstract void closeWriter();

    public Command build(List<String> args){
        String co = args.get(0);
        List<String> parameters = new ArrayList<String>();
        for(int i=1;i<args.size();i++){
            parameters.add(args.get(i));
        }
        String[] params = new String[parameters.size()];
        for(int i =0; i<parameters.size(); i++){
            params[i] = parameters.get(i);
        }
        Command command = new Command(co, params);
        return command;
    }

}
