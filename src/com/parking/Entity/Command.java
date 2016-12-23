package com.parking.Entity;

/**
 * Created by siddhahastmohapatra on 23/12/16.
 */
public class Command {

    private String command;
    private String[] paramenters;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String[] getParamenters() {
        return paramenters;
    }

    public void setParamenters(String[] paramenters) {
        this.paramenters = paramenters;
    }

    public Command(String command, String[] parameters){
        this.command = command;
        this.paramenters = parameters;
    }



}
